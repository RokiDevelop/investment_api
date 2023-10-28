package com.kiryukhin.portfolio_service.services.trading;

import com.kiryukhin.portfolio_service.entities.*;

import java.util.List;

public interface ITradingOperationService<T, ID> {
    T executeTradingOperation(Stock stock, ID amount, PortfolioEntity portfolio, TradingOperationType tradingOperationType);

    List<T> findOperationByPortfolioIdAndStockId(ID portfolioId, ID stockId);

    List<T> findOperationByPortfolioId(ID id);
}
