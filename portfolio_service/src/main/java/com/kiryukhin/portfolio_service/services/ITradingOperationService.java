package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;
import com.kiryukhin.portfolio_service.entities.TradingOperation;

public interface ITradingOperationService<T> {
    TradingOperation buy(Stock stock, Long amount, PortfolioEntity portfolio);
}
