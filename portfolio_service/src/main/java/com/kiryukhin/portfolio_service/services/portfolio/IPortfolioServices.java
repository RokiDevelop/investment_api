package com.kiryukhin.portfolio_service.services.portfolio;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;

import java.util.Optional;

public interface IPortfolioServices {
    Optional<PortfolioEntity> getPortfolioDataByUserId(Long id);
    Optional<PortfolioEntity> getPortfolioDataById(Long id);
    PortfolioEntity savePortfolio(PortfolioEntity portfolio);
}
