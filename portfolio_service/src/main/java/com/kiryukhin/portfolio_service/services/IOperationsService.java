package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.controllers.model.RequestBuyStockDto;
import com.kiryukhin.portfolio_service.controllers.model.RequestGetPortfolioInfoDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IOperationsService{
    ResponseEntity<?> createPortfolio(Principal principal);
    ResponseEntity<?> buyStock(RequestBuyStockDto requestBuyDto, Principal principal);
    void sellStock();
    ResponseEntity<?> getPortfolioInfo(RequestGetPortfolioInfoDto request, Principal principal);
}
