package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.controllers.model.RequestBuyStockDto;
import com.kiryukhin.portfolio_service.controllers.model.RequestGetPortfolioInfoDto;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.security.securityEntities.User;
import com.kiryukhin.portfolio_service.security.services.UserService;
import com.kiryukhin.portfolio_service.system.exception.PortfolioNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.StockNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class OperationService implements IOperationsService {
    private final IPortfolioServices portfolioServices;
    private final UserService userService;
    private final ITradingOperationService tradingOperationService;
    private final IStockService stockService;

    @Override
    public ResponseEntity<?> createPortfolio(Principal principal) {
        var user = getUserByPrincipal(principal);

        var portfolio = new PortfolioEntity();
        portfolio.setUser(user);
        portfolio.setAssetStocks(new HashSet<>());

        return ResponseEntity.ok(portfolioServices.savePortfolio(portfolio));
    }

    @Override
    public ResponseEntity<?> buyStock(RequestBuyStockDto requestBuyDto, Principal principal) {
        var user = getUserByPrincipal(principal);
        var portfolio = getPortfolioByUser(user);

        var ticker = requestBuyDto.getTicker();
        var stock = stockService.findStock(ticker).orElseThrow(() -> new StockNotFoundException("Stock not found!"));

        var amount = requestBuyDto.getAmount();
        var tradingOperation = tradingOperationService.buy(stock, amount, portfolio);

        return ResponseEntity.ok(tradingOperation);
    }

    @Override
    public void sellStock() {

    }

    @Override
    public ResponseEntity<?> getPortfolioInfo(RequestGetPortfolioInfoDto request, Principal principal) {
        var user = getUserByPrincipal(principal);

        var portfolio = getPortfolioByUser(user);
        return ResponseEntity.ok(portfolio);
    }

    private PortfolioEntity getPortfolioByUser(User user) {
        var portfolio = portfolioServices.getPortfolioDataByUserId(user.getId());
        return portfolio.orElseThrow(() -> {
            throw new PortfolioNotFoundException(String.format("Portfolio by user %S not found.", user.getUsername()));
        });
    }

    private User getUserByPrincipal(Principal principal) {
        var username = principal.getName();
        return userService.findUser(username);
    }
}
