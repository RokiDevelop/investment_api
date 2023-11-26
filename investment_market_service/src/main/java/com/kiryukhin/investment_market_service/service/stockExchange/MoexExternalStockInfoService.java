package com.kiryukhin.investment_market_service.service.stockExchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "moex-service")
public interface MoexExternalStockInfoService{

    @GetMapping("/{ticker}.json")
    String getInfoAboutTicker(@PathVariable String ticker,
                              @RequestParam("iss.meta") String meta,
                              @RequestParam("iss.only") String only);
}
