package com.kiryukhin.portfolio_service.repositories.tradingOperationType;

import java.util.Optional;

public interface ITradingOperationTypeRepository<T, ID> {
    Optional<T> findTradingOperationTypeByType(String operationType);
}
