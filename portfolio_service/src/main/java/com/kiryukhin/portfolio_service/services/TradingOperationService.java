package com.kiryukhin.portfolio_service.services;

import com.kiryukhin.portfolio_service.entities.PortfolioEntity;
import com.kiryukhin.portfolio_service.entities.Stock;
import com.kiryukhin.portfolio_service.entities.TradingOperation;
import com.kiryukhin.portfolio_service.repositories.ITradingOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TradingOperationService implements ITradingOperationService<TradingOperation> {
    private final ITradingOperationRepository tradingOperationRepository;

    @Override
    public TradingOperation buy(Stock stock, Long amount, PortfolioEntity portfolio) {
        TradingOperation tradingOperation = new TradingOperation();
        tradingOperation.setOperationDate(LocalDateTime.now());
        //TODO: get price into feign client
        tradingOperation.setPrice(100.0);
        tradingOperation.setAmount(amount);
        tradingOperation.setPortfolio(portfolio);
        tradingOperation.setStock(stock);

        return tradingOperationRepository.save(tradingOperation);
    }
}
