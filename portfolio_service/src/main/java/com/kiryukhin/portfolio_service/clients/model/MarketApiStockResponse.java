package com.kiryukhin.portfolio_service.clients.model;


public record MarketApiStockResponse(
        String ticker,
        Double price
) {
}
