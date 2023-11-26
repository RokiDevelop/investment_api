package com.kiryukhin.portfolio_service.clients;

import com.kiryukhin.portfolio_service.clients.model.MarketApiStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "market-service")
public interface ExternalMarketApi {

    @GetMapping("/getByTicker/{ticker}")
    MarketApiStockResponse getMarket(@PathVariable String ticker);
}
