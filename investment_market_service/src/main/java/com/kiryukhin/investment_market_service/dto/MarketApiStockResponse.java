package com.kiryukhin.investment_market_service.dto;

public record MarketApiStockResponse(
        String ticker,
        Double price
) {
}
