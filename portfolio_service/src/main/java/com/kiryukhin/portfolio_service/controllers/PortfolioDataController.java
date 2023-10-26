package com.kiryukhin.portfolio_service.controllers;

import com.kiryukhin.portfolio_service.controllers.model.requests.RequestBuyStockDto;
import com.kiryukhin.portfolio_service.controllers.model.responses.ResponseGetPortfolioInfoDto;
import com.kiryukhin.portfolio_service.services.IOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PortfolioDataController {
    private final IOperationsService operationsService;

    @PostMapping("/create_portfolio")
    public ResponseEntity<?> createPortfolio(Principal principal) {
        return operationsService.createPortfolio(principal);
    }

    @PostMapping("/buy_stock")
    public ResponseEntity<?> buyStock(@RequestBody RequestBuyStockDto request, Principal principal) {
        return operationsService.buyStock(request, principal);
    }

    @GetMapping("/portfolio_info")
    public ResponseEntity<ResponseGetPortfolioInfoDto> getInfo(Principal principal) {
        return operationsService.getPortfolioInfo(principal);
    }

//    @PostMapping("/sellStock")
//    public ResponseEntity<?> sellStock(@RequestBody RequestBuyDto requestBuyDto, Principal principal,Authentication authentication) {
//        authentication.getName();
//        return portfolioDataService.buyStack(requestBuyDto, principal);
//    }
}
