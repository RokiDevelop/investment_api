package com.kiryukhin.investment_market_service.service;

import com.kiryukhin.investment_market_service.dto.MarketApiStockResponse;
import com.kiryukhin.investment_market_service.dto.MarketApiStocksResponse;
import com.kiryukhin.investment_market_service.parser.Parser;
import com.kiryukhin.investment_market_service.service.stockExchange.IExchangeExternalShockInfoService;
import com.kiryukhin.investment_market_service.system.exception.parseException.ParsingResultDataIsMissingException;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockbrokerService implements IStockbrokerService {

    private final IExchangeExternalShockInfoService externalStockInfoService;
    private final Parser<MarketApiStockResponse> parser;

    @Async
    public CompletableFuture<String> getInfoFromMoex(String ticker) {
        return CompletableFuture.completedFuture(externalStockInfoService.getInfoAboutTicker(ticker));
    }

    @Override
    public MarketApiStockResponse getStockPriceInfoByTicker(String ticker) {
        String info = getInfoFromMoex(ticker).join();
        return parse(info);
    }

    @Override
    public MarketApiStocksResponse getStockPriceInfoByTickers(List<String> tickers) {
        List<CompletableFuture<String>> cfList = new ArrayList<>();
        tickers.forEach(ticker -> cfList.add(getInfoFromMoex(ticker)));

        List<MarketApiStockResponse> marketApiStockResponseList =
                cfList.stream()
                        .map(CompletableFuture::join)
                        .map(this::parse)
                        .collect(Collectors.toList());

        return new MarketApiStocksResponse(marketApiStockResponseList);
    }

    private MarketApiStockResponse parse(String data) {
        try {
            return parser.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (ParsingResultDataIsMissingException e) {
            return new MarketApiStockResponse(null, null);
        }
    }
}
