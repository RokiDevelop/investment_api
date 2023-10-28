package com.kiryukhin.portfolio_service.repositories.assetStock;

import com.kiryukhin.portfolio_service.entities.AssetStock;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetStockRepository extends IAssetStockRepository<AssetStock, Long>,
        JpaRepository<AssetStock, Long> {

    @Override
    AssetStock findByStockAndPortfolio(Stock stock, PortfolioEntity portfolio);

    @Override
    AssetStock save(AssetStock assetStock);
}
