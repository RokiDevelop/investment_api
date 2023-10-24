package com.kiryukhin.portfolio_service.repositories;

import com.kiryukhin.portfolio_service.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends IStockRepository, JpaRepository<Stock, Long> {
}
