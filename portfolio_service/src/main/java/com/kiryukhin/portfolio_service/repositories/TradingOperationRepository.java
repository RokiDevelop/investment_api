package com.kiryukhin.portfolio_service.repositories;

import com.kiryukhin.portfolio_service.entities.TradingOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingOperationRepository extends ITradingOperationRepository<TradingOperation>, JpaRepository<TradingOperation, Long> {
}
