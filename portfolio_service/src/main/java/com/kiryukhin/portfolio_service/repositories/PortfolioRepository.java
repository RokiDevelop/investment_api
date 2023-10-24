package com.kiryukhin.portfolio_service.repositories;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long>, IPortfolioRepository<PortfolioEntity> {

    @Override
    List<PortfolioEntity> findAll();

    @Override
    Optional<PortfolioEntity> findById(Long id);

    @Override
    <S extends PortfolioEntity> S save(S entity);

    @Override
    void deleteById(Long id);

    @Override
    void delete(PortfolioEntity entity);

    Optional<PortfolioEntity> findByUserId(Long user_id);
}
