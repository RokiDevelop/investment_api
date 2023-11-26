package com.kiryukhin.investment_market_service.parser;

import org.json.simple.parser.ParseException;

public interface Parser<T> {
    T parse(String s) throws ParseException;
}
