package com.kiryukhin.portfolio_service.repositories;

import com.kiryukhin.portfolio_service.entities.Stock;

import java.util.Optional;

public interface IStockRepository {
    Optional<Stock> findStockByTicker(String ticker);
}
