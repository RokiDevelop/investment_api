package com.kiryukhin.portfolio_service.controllers.model;

public record StockDto(String ticker, long amount, double priceNow, double priceBuy) {
}
