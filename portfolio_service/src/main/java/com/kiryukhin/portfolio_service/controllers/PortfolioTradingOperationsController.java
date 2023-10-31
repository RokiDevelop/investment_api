package com.kiryukhin.portfolio_service.controllers;

import com.kiryukhin.portfolio_service.controllers.model.requests.TradingOperationRequest;
import com.kiryukhin.portfolio_service.controllers.model.responses.TradingOperationResponse;
import com.kiryukhin.portfolio_service.services.IOperationsGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/tradingOperation")
@RequiredArgsConstructor
public class PortfolioTradingOperationsController {
    private final IOperationsGeneralService operationsService;

    @PostMapping("/createTradingOperation")
    public ResponseEntity<TradingOperationResponse.RecordTradingOperationResponse> createOperation(
            @RequestBody TradingOperationRequest.RecordTradingOperationRequest request,
            Principal principal) {
        var response = operationsService.createTradingOperation(request, principal);
        operationsService.updateAssetStockByRequest(request, principal);

        return response;
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<TradingOperationResponse.TradingOperationListResponse> getByTicker(
            @PathVariable String ticker, Principal principal){
        return operationsService.getTradingOperationListByTicker(ticker, principal);
    }

    @GetMapping("/getAll")
    public ResponseEntity<TradingOperationResponse.TradingOperationListResponse> getAll(Principal principal){
        return operationsService.getTradingOperationList(principal);
    }
}
