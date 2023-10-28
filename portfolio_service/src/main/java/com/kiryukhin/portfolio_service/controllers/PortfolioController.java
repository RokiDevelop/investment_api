package com.kiryukhin.portfolio_service.controllers;

import com.kiryukhin.portfolio_service.controllers.model.responses.PortfolioInfoResponse;
import com.kiryukhin.portfolio_service.services.IOperationsGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final IOperationsGeneralService operationsService;

    @PostMapping("/create_portfolio")
    public ResponseEntity<?> createPortfolio(Principal principal) {
        return operationsService.createPortfolio(principal);
    }

    @GetMapping("/portfolio_info")
    public ResponseEntity<PortfolioInfoResponse.GetPortfolioInfoResponse> getInfo(Principal principal) {
        return operationsService.getPortfolioInfo(principal);
    }
}
