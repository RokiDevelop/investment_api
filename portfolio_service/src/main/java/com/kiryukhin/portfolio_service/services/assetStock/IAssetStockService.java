package com.kiryukhin.portfolio_service.services.assetStock;

import com.kiryukhin.portfolio_service.entities.AssetStock;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;

import java.util.List;


public interface IAssetStockService {

    AssetStock findByStockAndPortfolio(Stock stock, PortfolioEntity portfolio);

    AssetStock save(AssetStock assetStock);

    void saveAll(List<AssetStock> assetStockList);

    void deleteAllByPortfolio(PortfolioEntity portfolio);
}

