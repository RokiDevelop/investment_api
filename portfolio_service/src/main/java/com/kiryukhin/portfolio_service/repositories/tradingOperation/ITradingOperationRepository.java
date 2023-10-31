package com.kiryukhin.portfolio_service.repositories.tradingOperation;

import java.util.List;
import java.util.Optional;


public interface ITradingOperationRepository<T, ID>{
    List<T> findAll();
    Optional<T> findById(ID id);

    T save(T t);

    void deleteById(ID id);
    void delete(T t);

    List<T> findOperationsByPortfolioIdAndStockId(Long portfolioId, Long stockId);

    List<T> findOperationsByPortfolioId(ID portfolioId);
}
