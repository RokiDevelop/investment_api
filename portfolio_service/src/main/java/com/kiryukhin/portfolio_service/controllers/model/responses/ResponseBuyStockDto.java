package com.kiryukhin.portfolio_service.controllers.model.responses;

public record ResponseBuyStockDto(
        String portfolio_id,
        String ticker,
        String amount,
        String price
) {
}
