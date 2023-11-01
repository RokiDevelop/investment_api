package com.kiryukhin.portfolio_service.repositories.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPortfolioRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    void deleteById(ID id);
    void delete(T t);
    <S extends T> S saveAndFlush(S entity);

    Optional<T> findByUserId(Long id);

}
