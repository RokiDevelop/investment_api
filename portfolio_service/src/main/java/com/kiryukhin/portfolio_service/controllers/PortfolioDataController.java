package com.kiryukhin.portfolio_service.controllers;

import com.kiryukhin.portfolio_service.controllers.model.RequestBuyStockDto;
import com.kiryukhin.portfolio_service.controllers.model.RequestGetPortfolioInfoDto;
import com.kiryukhin.portfolio_service.services.IOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping()
public class PortfolioDataController {
    private final IOperationsService operationsService;

    @Autowired
    public PortfolioDataController(IOperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @PostMapping("/create_portfolio")
    public ResponseEntity<?> createPortfolio(Principal principal) {
        return operationsService.createPortfolio(principal);
    }

    @PostMapping("/buy_stock")
    public ResponseEntity<?> buyStock(@RequestBody RequestBuyStockDto request, Principal principal) {
        return operationsService.buyStock(request, principal);
    }

    @GetMapping("/portfolio_info")
    public ResponseEntity<?> getInfo(@RequestBody RequestGetPortfolioInfoDto request, Principal principal) {
        return operationsService.getPortfolioInfo(request, principal);
    }

//    @PostMapping("/sellStock")
//    public ResponseEntity<?> sellStock(@RequestBody RequestBuyDto requestBuyDto, Principal principal,Authentication authentication) {
//        authentication.getName();
//        return portfolioDataService.buyStack(requestBuyDto, principal);
//    }
}
