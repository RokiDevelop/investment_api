package com.kiryukhin.portfolio_service.repositories.assetStock;

import com.kiryukhin.portfolio_service.entities.AssetStock;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;

import java.util.Optional;

public interface IAssetStockRepository<T, ID> {
    T findByStockAndPortfolio(Stock stock, PortfolioEntity portfolio);

    T save(T assetStock);
}
