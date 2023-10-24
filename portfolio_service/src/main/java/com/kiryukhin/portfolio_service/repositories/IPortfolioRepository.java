package com.kiryukhin.portfolio_service.repositories;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;

import java.util.List;
import java.util.Optional;

public interface IPortfolioRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    <S extends T> PortfolioEntity save(S entity);
    void deleteById(Long id);
    void delete(T t);

    Optional<T> findByUserId(Long id);

}
