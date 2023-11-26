package com.kiryukhin.portfolio_service.repositories.portfolio;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends IPortfolioRepository<PortfolioEntity, Long>,
        JpaRepository<PortfolioEntity, Long> {

}
