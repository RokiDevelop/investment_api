package com.kiryukhin.portfolio_service.services;

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
import com.kiryukhin.portfolio_service.services.trading.ITradingOperationService;
import com.kiryukhin.portfolio_service.services.tradingOperationType.ITradingOperationTypeService;
import com.kiryukhin.portfolio_service.system.exception.PortfolioNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.StockNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.TradingOperationTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public ResponseEntity<?> createPortfolio(Principal principal) {
        var user = getUserByPrincipal(principal);

        var portfolio = new PortfolioEntity();
        portfolio.setUser(user);
        portfolio.setAssetStocks(new HashSet<>());

        return ResponseEntity.ok(portfolioServices.savePortfolio(portfolio));
    }

    @Override
    public ResponseEntity<TradingOperationResponse.RecordTradingOperationResponse> createTradingOperation(
            TradingOperationRequest.RecordTradingOperationRequest requestBuyDto,
            Principal principal) {
        var user = getUserByPrincipal(principal);
        var portfolio = getPortfolioByUser(user);

        var ticker = requestBuyDto.ticker();
        var stock = findStockByTicker(ticker);

        var operationType = requestBuyDto.operationType();
        var tradingOperationType = tradingOperationTypeService.findTradingOperationTypeByType(operationType)
                .orElseThrow(() -> {
                    final String s = String.format("Operation type '%s' not found!", operationType);

                    log.debug(s);
                    return new TradingOperationTypeNotFoundException(s);
                });

        var amount = requestBuyDto.amount();
        var tradingOperation = tradingOperationService.executeTradingOperation(stock, amount, portfolio, tradingOperationType);

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
        var user = getUserByPrincipal(principal);
        var portfolio = getPortfolioByUser(user);
        var ticker = request.ticker();
        var stock = findStockByTicker(ticker);

        var tradingOperationList = tradingOperationService.findOperationByPortfolioIdAndStockId(portfolio.getId(), stock.getId());
        if (tradingOperationList != null && !tradingOperationList.isEmpty()) {

            var assetStock = assetStockService.findByStockAndPortfolio(stock, portfolio);
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

    public void updateAllAssetStockByPrincipal(Principal principal) {
        var user = getUserByPrincipal(principal);
        var portfolio = getPortfolioByUser(user);
        List<AssetStock> assetStocksOld = portfolio.getAssetStocks().stream().toList();

        List<TradingOperation> tradingOperationList = tradingOperationService.findOperationByPortfolioId(portfolio.getId());
        List<Stock> stockList = tradingOperationList.stream().map(TradingOperation::getStock).toList();
//        stockList.stream().collect(Collectors.toList()).forEach(stock -> !assetStocks.contains());
//
//        List<AssetStock> assetStockList = tradingOperationList.stream();
    }

    @Override
    public ResponseEntity<PortfolioInfoResponse.GetPortfolioInfoResponse> getPortfolioInfo(Principal principal) {
        var user = getUserByPrincipal(principal);
        var portfolio = getPortfolioByUser(user);

        return ResponseEntity.ok(
                new PortfolioInfoResponse.GetPortfolioInfoResponse(
                        portfolio.getUser().getUsername(),
                        portfolio.getId().toString(),
                        List.of(portfolio.getAssetStocks().stream().toList().toString())
                ));
    }

    private PortfolioEntity getPortfolioByUser(User user) {
        var portfolio = portfolioServices.getPortfolioDataByUserId(user.getId());
        return portfolio.orElseThrow(() -> {
            final String s = String.format("Portfolio by user %s not found.", user.getUsername());

            log.debug(s);
            return new PortfolioNotFoundException(s);
        });
    }

    private Stock findStockByTicker(String ticker) {
        return stockService.findStock(ticker)
                .orElseThrow(() -> {
                    final String s = String.format("Stock with ticker '%s' not found!", ticker);

                    log.debug(s);
                    return new StockNotFoundException(s);
                });
    }

    private User getUserByPrincipal(Principal principal) {
        var username = principal.getName();
        return userService.findUser(username);
    }

    private Long calculateAmountStock(List<TradingOperation> tradingOperationList) {
        Long amountBuy = tradingOperationList.stream()
                .filter(tradingOperationType ->
                        EnumOperationType.BUY
                                .equalsName(
                                        tradingOperationType.getTradingOperationType().getType()))
                .mapToLong(TradingOperation::getAmount).sum();

        Long amountSell = tradingOperationList.stream()
                .filter(tradingOperationType ->
                        EnumOperationType.SELL
                                .equalsName(
                                        tradingOperationType.getTradingOperationType().getType()))
                .mapToLong(TradingOperation::getAmount).sum();

        return amountBuy - amountSell;
    }
}
