package com.kiryukhin.investment_market_service.dto;

import java.util.List;

public record MarketApiStocksResponse(
        List<MarketApiStockResponse> responses
) {
}
