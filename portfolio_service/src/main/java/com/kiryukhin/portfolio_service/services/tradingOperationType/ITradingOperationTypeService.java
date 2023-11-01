package com.kiryukhin.portfolio_service.services.tradingOperationType;

import com.kiryukhin.portfolio_service.entities.enums.EnumOperationType;
import com.kiryukhin.portfolio_service.entities.TradingOperationType;

import java.util.Optional;

public interface ITradingOperationTypeService {

    Optional<TradingOperationType> findTradingOperationTypeByType(EnumOperationType operationType);

}
