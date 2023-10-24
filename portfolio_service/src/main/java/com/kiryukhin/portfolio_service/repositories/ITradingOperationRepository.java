package com.kiryukhin.portfolio_service.repositories;

import com.kiryukhin.portfolio_service.entities.TradingOperation;

import java.util.List;
import java.util.Optional;

public interface ITradingOperationRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    <S extends T> TradingOperation save(S entity);
    void deleteById(Long id);
    void delete(T t);
}
