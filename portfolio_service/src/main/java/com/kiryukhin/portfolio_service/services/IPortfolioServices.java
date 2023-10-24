package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.controllers.model.RequestBuyStockDto;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.security.securityEntities.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface IPortfolioServices {
    Optional<PortfolioEntity> getPortfolioDataByUserId(Long id);
    PortfolioEntity getPortfolioDataById(Integer id);

    PortfolioEntity savePortfolio(PortfolioEntity portfolio);
}
