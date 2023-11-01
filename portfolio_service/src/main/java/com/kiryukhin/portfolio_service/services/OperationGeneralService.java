package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.clients.ExternalMarketApi;
import com.kiryukhin.portfolio_service.clients.model.MarketApiStockResponse;
import com.kiryukhin.portfolio_service.controllers.model.requests.TradingOperationRequest;
import com.kiryukhin.portfolio_service.controllers.model.responses.PortfolioInfoResponse;
import com.kiryukhin.portfolio_service.controllers.model.responses.TradingOperationResponse;
import com.kiryukhin.portfolio_service.entities.*;
import com.kiryukhin.portfolio_service.entities.enums.EnumOperationType;
import com.kiryukhin.portfolio_service.security.securityEntities.User;
import com.kiryukhin.portfolio_service.security.services.UserService;
import com.kiryukhin.portfolio_service.services.assetStock.IAssetStockService;
import com.kiryukhin.portfolio_service.services.portfolio.IPortfolioServices;
import com.kiryukhin.portfolio_service.services.stock.IStockService;
import com.kiryukhin.portfolio_service.services.tradingOperation.ITradingOperationService;
import com.kiryukhin.portfolio_service.services.tradingOperationType.ITradingOperationTypeService;
import com.kiryukhin.portfolio_service.system.exception.notFound.PortfolioNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.notFound.StockNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.notFound.TradingOperationTypeNotFoundException;

import com.kiryukhin.portfolio_service.system.exception.serviceNotResponses.ExternalServiceNotResponses;
import com.kiryukhin.portfolio_service.system.exception.serviceNotResponses.ExternalServiceReturnNull;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationGeneralService implements IOperationsGeneralService {
    private final IPortfolioServices portfolioServices;
    private final ITradingOperationService<TradingOperation, Long> tradingOperationService;
    private final IStockService stockService;
    private final IAssetStockService assetStockService;
    private final ITradingOperationTypeService tradingOperationTypeService;
    private final UserService userService;
    private final ExternalMarketApi externalMarketApi;

    @Override
    public ResponseEntity<?> createPortfolio(Principal principal) {
        try {
            getPortfolioByPrincipal(principal);
        } catch (PortfolioNotFoundException ex) {
            String username = principal.getName();
            User user = userService.findUser(username);

            PortfolioEntity portfolio = new PortfolioEntity();
            portfolio.setUser(user);
            portfolio.setAssetStocks(new HashSet<>());

            return ResponseEntity.ok(portfolioServices.savePortfolio(portfolio));
        }
        return ResponseEntity.badRequest().body("Portfolio already exists.");
    }

    @Override
    public ResponseEntity<TradingOperationResponse.RecordTradingOperationResponse> createTradingOperation(
            TradingOperationRequest.RecordTradingOperationRequest requestBuyDto,
            Principal principal) {
        PortfolioEntity portfolio = getPortfolioByPrincipal(principal);
        String ticker = requestBuyDto.ticker();
        Stock stock = findStockByTicker(ticker);

        var operationType = requestBuyDto.operationType();
        var tradingOperationType = tradingOperationTypeService.findTradingOperationTypeByType(operationType)
                .orElseThrow(() -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Operation type ").append(operationType).append(" not found!");
                    log.debug(builder.toString());
                    return new TradingOperationTypeNotFoundException(builder.toString());
                });

        Double price = getPriceByTicker(ticker);

        long amount = requestBuyDto.amount();
        TradingOperation tradingOperation =
                tradingOperationService.executeTradingOperation(stock, amount, portfolio, tradingOperationType, price);

        return ResponseEntity.ok(
                new TradingOperationResponse.RecordTradingOperationResponse(
                tradingOperation.getId().toString(),
                tradingOperation.getPortfolio().toString(),
                tradingOperation.getStock().getTicker(),
                tradingOperation.getAmount().toString(),
                tradingOperation.getPrice().toString()
        ));
    }

    @Override
    public void updateAssetStockByRequest(TradingOperationRequest.RecordTradingOperationRequest request, Principal principal) {
        PortfolioEntity portfolio = getPortfolioByPrincipal(principal);
        String ticker = request.ticker();
        Stock stock = findStockByTicker(ticker);
        List<TradingOperation> tradingOperationList = tradingOperationService
                .findOperationsByPortfolioIdAndStockId(portfolio.getId(), stock.getId());

        if (!tradingOperationList.isEmpty()) {
            AssetStock assetStock = assetStockService.findByStockAndPortfolio(stock, portfolio);
            if (assetStock == null) {
                assetStock = new AssetStock();
                assetStock.setPortfolio(portfolio);
                assetStock.setStock(stock);
            }

            var amount = calculateAmountStock(tradingOperationList);
            assetStock.setAmount(amount);
            assetStockService.save(assetStock);
        }
    }

    @Override
    public ResponseEntity<?> updateAllAssetStockByPrincipal(Principal principal) {
        var portfolio = getPortfolioByPrincipal(principal);
        List<TradingOperation> tradingOperationList =
                tradingOperationService.findOperationsByPortfolioId(portfolio.getId());

        if (!tradingOperationList.isEmpty()) {
            Map<Stock, List<TradingOperation>> map = tradingOperationList.stream()
                    .collect(Collectors.groupingBy(
                            TradingOperation::getStock,
                            Collectors.toList()));

            List<AssetStock> assetStockList = map.entrySet()
                    .stream()
                    .map(entry -> new AssetStock(entry.getKey(), calculateAmountStock(entry.getValue()), portfolio)
                    )
                    .toList();

            assetStockService.deleteAllByPortfolio(portfolio);
            assetStockService.saveAll(assetStockList);
            return ResponseEntity.ok("User portfolio successfully updated!");
        }
        return ResponseEntity.ok("User has not made any trading operations!");
    }

    @Override
    public ResponseEntity<TradingOperationResponse.TradingOperationListResponse> getTradingOperationList(Principal principal) {
        PortfolioEntity portfolio = getPortfolioByPrincipal(principal);

        List<TradingOperation> operationList =
                tradingOperationService.findOperationsByPortfolioId(portfolio.getId());
        return ResponseEntity.ok(new TradingOperationResponse.TradingOperationListResponse(
                operationList.stream().map(TradingOperation::toString).toList()));
    }

    @Override
    public ResponseEntity<TradingOperationResponse.TradingOperationListResponse> getTradingOperationListByTicker(
            String ticker,
            Principal principal) {
        PortfolioEntity portfolio = getPortfolioByPrincipal(principal);
        Stock stock = findStockByTicker(ticker);

        var operationList =
                tradingOperationService.findOperationsByPortfolioIdAndStockId(portfolio.getId(), stock.getId());
        return ResponseEntity.ok(new TradingOperationResponse.TradingOperationListResponse(
                operationList.stream().map(TradingOperation::toString).toList()));
    }

    @Override
    public ResponseEntity<PortfolioInfoResponse.GetPortfolioInfoResponse> getPortfolioInfo(Principal principal) {
        var portfolio = getPortfolioByPrincipal(principal);

        return ResponseEntity.ok(
                new PortfolioInfoResponse.GetPortfolioInfoResponse(
                        portfolio.getUser().getUsername(),
                        portfolio.getId().toString(),
                        portfolio.getAssetStocks().stream().map(AssetStock::toString).toList()
                ));
    }

    private Double getPriceByTicker(String ticker) {
        try {
            MarketApiStockResponse notNullResponse = Optional.ofNullable(externalMarketApi.getMarket(ticker))
                    .orElseThrow(() -> {
                        StringBuilder builder = new StringBuilder();
                        builder.append("Market Api by GetPrice ticker:'").append(ticker).append("' request return Null!");
                        log.debug(builder.toString());
                        return new ExternalServiceReturnNull(builder.toString());
                    });
            return notNullResponse.price();

        } catch (FeignException e) {
            int httpStatus = e.status();
            String responseBody = e.getMessage();
            log.debug(responseBody);
            throw new ExternalServiceNotResponses(httpStatus, responseBody);
        }
    }

    private Stock findStockByTicker(String ticker) {
        return stockService.findStock(ticker)
                .orElseThrow(() -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Stock with ticker ").append(ticker).append(" not found!");
                    log.debug(builder.toString());
                    return new StockNotFoundException(builder.toString());
                });
    }

    private PortfolioEntity getPortfolioByPrincipal(Principal principal) {
        var user = getUserByPrincipal(principal);
        var portfolio = portfolioServices.getPortfolioDataByUserId(user.getId());

        return portfolio.orElseThrow(() -> {
            StringBuilder builder = new StringBuilder();
            builder.append("Portfolio by user ").append(user.getUsername()).append(" not found!");
            log.debug(builder.toString());
            return new PortfolioNotFoundException(builder.toString());
        });
    }

    private User getUserByPrincipal(Principal principal) {
        var username = principal.getName();
        return userService.findUser(username);
    }

    private Long calculateAmountStock(List<TradingOperation> tradingOperationList) {
        Long amountBuy = tradingOperationList.stream()
                .filter(tradingOperationType ->
                        EnumOperationType.BUY.equalsName(
                                tradingOperationType.getTradingOperationType().getType()))
                .mapToLong(TradingOperation::getAmount).sum();
        Long amountSell = tradingOperationList.stream()
                .filter(tradingOperationType ->
                        EnumOperationType.SELL.equalsName(
                                tradingOperationType.getTradingOperationType().getType()))
                .mapToLong(TradingOperation::getAmount).sum();

        return amountBuy - amountSell;
    }
}
