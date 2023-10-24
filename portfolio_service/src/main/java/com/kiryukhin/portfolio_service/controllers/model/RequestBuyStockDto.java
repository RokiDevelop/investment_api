package com.kiryukhin.portfolio_service.controllers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestBuyStockDto {
    private String ticker;
    private Long amount;
}
