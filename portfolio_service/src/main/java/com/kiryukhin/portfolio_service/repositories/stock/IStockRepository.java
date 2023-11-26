package com.kiryukhin.portfolio_service.repositories.stock;

import java.util.Optional;

public interface IStockRepository<T, ID> {
    Optional<T> findStockByTicker(String ticker);

}
