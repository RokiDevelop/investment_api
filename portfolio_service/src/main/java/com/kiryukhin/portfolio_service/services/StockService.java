package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.entities.Stock;
import com.kiryukhin.portfolio_service.repositories.IStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService{
    private final IStockRepository stockRepository;
    public Optional<Stock> findStock(String ticker){
        return stockRepository.findStockByTicker(ticker);
    }
}
