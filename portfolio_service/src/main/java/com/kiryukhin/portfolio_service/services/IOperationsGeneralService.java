package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.controllers.model.requests.TradingOperationRequest;
import com.kiryukhin.portfolio_service.controllers.model.responses.PortfolioInfoResponse;
import com.kiryukhin.portfolio_service.controllers.model.responses.TradingOperationResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IOperationsGeneralService {
    ResponseEntity<?> createPortfolio(
            Principal principal);

    ResponseEntity<TradingOperationResponse.TradingOperationListResponse> getTradingOperationListByTicker(String ticker, Principal principal);

    ResponseEntity<PortfolioInfoResponse.GetPortfolioInfoResponse> getPortfolioInfo(
            Principal principal);

    ResponseEntity<TradingOperationResponse.RecordTradingOperationResponse> createTradingOperation(
            TradingOperationRequest.RecordTradingOperationRequest requestBuyDto,
            Principal principal);

    void updateAssetStockByRequest(
            TradingOperationRequest.RecordTradingOperationRequest request,
            Principal principal);

    ResponseEntity<?> updateAllAssetStockByPrincipal(Principal principal);

    ResponseEntity<TradingOperationResponse.TradingOperationListResponse> getTradingOperationList(Principal principal);
}
