package com.kiryukhin.investment_market_service.parser;

import com.kiryukhin.investment_market_service.dto.MarketApiStockResponse;
import com.kiryukhin.investment_market_service.system.exception.parseException.ParsingResultDataIsMissingException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MoexStockParserPrice implements Parser<MarketApiStockResponse> {
    @Value("${moex.parse.desired-board-id}")
    private String desiredBoardId;
    @Value("${moex.parse.datalist}")
    private String datalist;
    @Value("${moex.parse.data-from-datalist}")
    private String dataFromDatalist;

    private final JSONParser parser = new JSONParser();

    public MarketApiStockResponse parse(String string) throws ParseException {
        JSONObject jsonObject = getJsonObjectIntoStringData(string);
        JSONArray data = getDataAsJsonArray(jsonObject);

        Optional<MarketApiStockResponse> result =
                getOptMarketApiStockResponseByBoardId(data, desiredBoardId);

        return result.orElseThrow(ParsingResultDataIsMissingException::new);
    }

    private JSONObject getJsonObjectIntoStringData(String string) throws ParseException {
        return (JSONObject) parser.parse(string);
    }

    private JSONArray getDataAsJsonArray(JSONObject jsonObject) {
        JSONObject marketdata = (JSONObject) jsonObject.get(datalist);
        return (JSONArray) marketdata.get(dataFromDatalist);
    }

    private Optional<MarketApiStockResponse> getOptMarketApiStockResponseByBoardId(JSONArray data, String desiredBoardId) {
        return data.stream()
                .filter(entry -> {
                    JSONArray stockData = (JSONArray) entry;
                    String boardId = (String) stockData.get(1);
                    return desiredBoardId.equals(boardId);
                })
                .map(entry -> {
                    JSONArray stockData = (JSONArray) entry;
                    String ticker = (String) stockData.get(0);
                    Double lastPrice = (Double) stockData.get(12);
                    return new MarketApiStockResponse(ticker,lastPrice);
                }).findFirst();
    }
}
