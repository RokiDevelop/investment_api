package com.kiryukhin.portfolio_service.services.assetStock;

import com.kiryukhin.portfolio_service.entities.AssetStock;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;
import com.kiryukhin.portfolio_service.repositories.assetStock.IAssetStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetStockService implements IAssetStockService {
    private final IAssetStockRepository<AssetStock, Long> assetStockRepository;

    @Override
    public AssetStock findByStockAndPortfolio(Stock stock, PortfolioEntity portfolio) {
        return assetStockRepository.findByStockAndPortfolio(stock, portfolio);
    }

    @Override
    public AssetStock save(AssetStock assetStock) {
        return assetStockRepository.save(assetStock);
    }
}
