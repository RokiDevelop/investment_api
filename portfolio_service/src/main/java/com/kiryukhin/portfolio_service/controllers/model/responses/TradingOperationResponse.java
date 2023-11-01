package com.kiryukhin.portfolio_service.controllers.model.responses;

import java.util.List;

public interface TradingOperationResponse {
    record RecordTradingOperationResponse(
            String tradingOperationId,
            String portfolioId,
            String ticker,
            String amount,
            String price
    ) {
    }

    record TradingOperationListResponse(
            List<String> tradingOperationList
    ) {
    }
}
