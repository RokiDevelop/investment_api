package com.kiryukhin.investment_market_service.service.stockExchange;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeExternalShockInfoService implements IExchangeExternalShockInfoService{
    @Value("${moex.response.metadata}")
    private String metadata;

    @Value("${moex.response.name-only-datalist}")
    private String datalist;

    private final MoexExternalStockInfoService moexExternalStockInfoService;

    @Override
    public String getInfoAboutTicker(String ticker) {
        return moexExternalStockInfoService.getInfoAboutTicker(ticker, metadata, datalist);
    }
}
