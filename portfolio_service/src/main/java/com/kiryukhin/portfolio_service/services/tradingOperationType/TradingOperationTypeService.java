package com.kiryukhin.portfolio_service.services.tradingOperationType;

import com.kiryukhin.portfolio_service.entities.enums.EnumOperationType;
import com.kiryukhin.portfolio_service.entities.TradingOperationType;
import com.kiryukhin.portfolio_service.repositories.tradingOperationType.ITradingOperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradingOperationTypeService implements ITradingOperationTypeService{
    private final ITradingOperationTypeRepository<TradingOperationType, Long> tradingOperationTypeRepository;

    @Override
    public Optional<TradingOperationType> findTradingOperationTypeByType(EnumOperationType operationType) {
//        return Optional.empty();
        return tradingOperationTypeRepository.findTradingOperationTypeByType(operationType.toString().toUpperCase());
    }
}
