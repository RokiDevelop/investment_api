package com.kiryukhin.investment_market_service.service;

import com.kiryukhin.investment_market_service.dto.MarketApiStockResponse;
import com.kiryukhin.investment_market_service.dto.MarketApiStocksResponse;

import java.util.List;

public interface IStockbrokerService {
    MarketApiStockResponse getStockPriceInfoByTicker(String ticker);

    MarketApiStocksResponse getStockPriceInfoByTickers(List<String> tickers);
}
