package com.kiryukhin.portfolio_service.services.assetStock;

import com.kiryukhin.portfolio_service.entities.AssetStock;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;


public interface IAssetStockService {

    AssetStock findByStockAndPortfolio(Stock stock, PortfolioEntity portfolio);

    AssetStock save(AssetStock assetStock);
}

