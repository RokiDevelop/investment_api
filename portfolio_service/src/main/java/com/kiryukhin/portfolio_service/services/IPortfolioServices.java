package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;

import java.util.Optional;

public interface IPortfolioServices {
    Optional<PortfolioEntity> getPortfolioDataByUserId(Long id);
    PortfolioEntity getPortfolioDataById(Integer id);

    PortfolioEntity savePortfolio(PortfolioEntity portfolio);
}
