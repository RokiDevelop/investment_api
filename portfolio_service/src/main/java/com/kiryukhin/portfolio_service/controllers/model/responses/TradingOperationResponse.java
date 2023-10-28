package com.kiryukhin.portfolio_service.controllers.model.responses;

public interface TradingOperationResponse {
    record RecordTradingOperationResponse(
            String tradingOperationId,
            String portfolioId,
            String ticker,
            String amount,
            String price
            ) {
    }
}
