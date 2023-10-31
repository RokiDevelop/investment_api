package com.kiryukhin.portfolio_service.services.tradingOperation;

import com.kiryukhin.portfolio_service.entities.*;

import java.util.List;

public interface ITradingOperationService<T, ID> {
    T executeTradingOperation(Stock stock,
                              ID amount,
                              PortfolioEntity portfolio,
                              TradingOperationType tradingOperationType,
                              Double price);

    List<T> findOperationsByPortfolioIdAndStockId(ID portfolioId, ID stockId);

    List<T> findOperationsByPortfolioId(ID id);
}
