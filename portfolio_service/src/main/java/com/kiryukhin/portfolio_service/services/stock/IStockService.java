package com.kiryukhin.portfolio_service.services.stock;

import com.kiryukhin.portfolio_service.entities.Stock;

import java.util.Optional;

public interface IStockService {
    Optional<Stock> findStock(String ticker);
}
