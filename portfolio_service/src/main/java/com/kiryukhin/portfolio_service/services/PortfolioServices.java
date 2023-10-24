package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.repositories.IPortfolioRepository;
import com.kiryukhin.portfolio_service.security.securityEntities.User;
import com.kiryukhin.portfolio_service.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioServices implements IPortfolioServices {
    private final IPortfolioRepository portfolioRepository;
    private final UserService userService;

    @Override
    public Optional<PortfolioEntity> getPortfolioDataByUserId(Long id) {
        return portfolioRepository.findByUserId(id);
    }

    @Override
    public PortfolioEntity getPortfolioDataById(Integer id) {
        return null;
    }

    @Override
    public PortfolioEntity savePortfolio(PortfolioEntity portfolio) {
        return portfolioRepository.save(portfolio);
    }

    private User getUserByPrincipal(Principal principal) {
        var username = principal.getName();
        return userService.findUser(username);
    }
}
