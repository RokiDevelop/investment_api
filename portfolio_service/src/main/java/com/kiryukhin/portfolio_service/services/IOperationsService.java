package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.controllers.model.requests.RequestBuyStockDto;
import com.kiryukhin.portfolio_service.controllers.model.requests.RequestGetPortfolioInfoDto;
import com.kiryukhin.portfolio_service.controllers.model.responses.ResponseGetPortfolioInfoDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IOperationsService{
    ResponseEntity<?> createPortfolio(Principal principal);
    ResponseEntity<?> buyStock(RequestBuyStockDto requestBuyDto, Principal principal);
    void sellStock();
    ResponseEntity<ResponseGetPortfolioInfoDto> getPortfolioInfo(Principal principal);
}
