package com.kiryukhin.portfolio_service.controllers.model.requests;

import com.kiryukhin.portfolio_service.entities.enums.EnumOperationType;

public interface TradingOperationRequest {

    record RecordTradingOperationRequest(
            String ticker,
            Long amount,
            EnumOperationType operationType
    ){}
}
