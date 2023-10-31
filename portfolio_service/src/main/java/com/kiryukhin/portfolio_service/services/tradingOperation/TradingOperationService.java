package com.kiryukhin.portfolio_service.services.tradingOperation;

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
    public TradingOperation executeTradingOperation(Stock stock,
                                                    Long amount,
                                                    PortfolioEntity portfolio,
                                                    TradingOperationType tradingOperationType,
                                                    Double price) {
        TradingOperation tradingOperation = new TradingOperation();
        tradingOperation.setOperationDate(LocalDateTime.now());
        tradingOperation.setPrice(price);
        tradingOperation.setAmount(amount);
        tradingOperation.setPortfolio(portfolio);
        tradingOperation.setStock(stock);
        tradingOperation.setTradingOperationType(tradingOperationType);

        return tradingOperationRepository.save(tradingOperation);
    }

    @Override
    public List<TradingOperation> findOperationsByPortfolioIdAndStockId(Long portfolioId, Long stockId) {
        return tradingOperationRepository.findOperationsByPortfolioIdAndStockId(portfolioId, stockId);
    }

    @Override
    public List<TradingOperation> findOperationsByPortfolioId(Long portfolioId) {
        return tradingOperationRepository.findOperationsByPortfolioId(portfolioId);
    }
}
