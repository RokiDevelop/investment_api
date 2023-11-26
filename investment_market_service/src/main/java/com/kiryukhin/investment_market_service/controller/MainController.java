package com.kiryukhin.investment_market_service.controller;


import com.kiryukhin.investment_market_service.dto.MarketApiStockResponse;
import com.kiryukhin.investment_market_service.dto.MarketApiStocksResponse;
import com.kiryukhin.investment_market_service.service.IStockbrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final IStockbrokerService stockbrokerService;

    @GetMapping("/getByTicker/{ticker}")
    MarketApiStockResponse getInfoAboutStockByTicker(@PathVariable String ticker) {
        return stockbrokerService.getStockPriceInfoByTicker(ticker);
    }

    @GetMapping(path = "/getByTickers/{tickers}", produces = {"application/json"})
    MarketApiStocksResponse getInfoAboutStockByTicker(@PathVariable List<String> tickers) {
        return stockbrokerService.getStockPriceInfoByTickers(tickers);
    }
}
