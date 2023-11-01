package com.kiryukhin.portfolio_service.services.portfolio;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.repositories.portfolio.IPortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioServices implements IPortfolioServices {
    private final IPortfolioRepository<PortfolioEntity,Long> portfolioRepository;

    @Override
    public Optional<PortfolioEntity> getPortfolioDataByUserId(Long id) {
        return portfolioRepository.findByUserId(id);
    }

    @Override
    public Optional<PortfolioEntity> getPortfolioDataById(Long id) {
        return portfolioRepository.findById(id);
    }


    @Override
    public PortfolioEntity savePortfolio(PortfolioEntity portfolio) {
        return portfolioRepository.saveAndFlush(portfolio);
    }
}
