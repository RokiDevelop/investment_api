package com.kiryukhin.portfolio_service.services.trading;

import com.kiryukhin.portfolio_service.entities.*;
import com.kiryukhin.portfolio_service.repositories.tradingOperation.ITradingOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradingOperationService implements ITradingOperationService<TradingOperation, Long> {

    private final ITradingOperationRepository<TradingOperation, Long> tradingOperationRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TradingOperation executeTradingOperation(Stock stock, Long amount, PortfolioEntity portfolio, TradingOperationType tradingOperationType) {
        TradingOperation tradingOperation = new TradingOperation();
        tradingOperation.setOperationDate(LocalDateTime.now());
        //TODO: get price into feign client
        tradingOperation.setPrice(100.0);
        tradingOperation.setAmount(amount);
        tradingOperation.setPortfolio(portfolio);
        tradingOperation.setStock(stock);
        tradingOperation.setTradingOperationType(tradingOperationType);

        return tradingOperationRepository.save(tradingOperation);
    }

    @Override
    public List<TradingOperation> findOperationByPortfolioIdAndStockId(Long portfolioId, Long stockId) {
        return tradingOperationRepository.findOperationByPortfolioIdAndStockId(portfolioId, stockId);
    }

    @Override
    public List<TradingOperation> findOperationByPortfolioId(Long portfolioId) {
        return tradingOperationRepository.findOperationByPortfolioId(portfolioId);
    }
}
