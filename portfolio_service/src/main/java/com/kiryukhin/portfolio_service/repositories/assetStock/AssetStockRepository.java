package com.kiryukhin.portfolio_service.repositories.assetStock;

import com.kiryukhin.portfolio_service.entities.AssetStock;
import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetStockRepository extends IAssetStockRepository<AssetStock>,
        JpaRepository<AssetStock, Long> {

    @Override
    AssetStock findByStockAndPortfolio(Stock stock, PortfolioEntity portfolio);

    @Override
    AssetStock save(AssetStock assetStock);

    @Override
    <S extends AssetStock> List<S> saveAll(Iterable<S> entities);

    default void deleteAllByPortfolio(PortfolioEntity portfolio) {
        List<AssetStock> assetStocks = findAllByPortfolio(portfolio);
        deleteAllById(assetStocks.stream().map(AssetStock::getId).toList());
    }

    List<AssetStock> findAllByPortfolio(PortfolioEntity portfolio);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);
}
