package com.kiryukhin.portfolio_service.repositories.tradingOperationType;
import com.kiryukhin.portfolio_service.entities.TradingOperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradingOperationTypeRepository extends ITradingOperationTypeRepository<TradingOperationType, Long>,
        JpaRepository<TradingOperationType, Long> {

    Optional<TradingOperationType> findTradingOperationTypeByType(String operationType);
}
