package com.devserbyn.twatch.service.answer.api.mainbot.impl;

import com.devserbyn.twatch.service.answer.api.mainbot.CurrencyAPIRequester;
import com.devserbyn.twatch.service.parser.mainbot.Currency;
import com.devserbyn.twatch.service.parser.mainbot.CurrencyParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyAPIRequesterImpl implements CurrencyAPIRequester {

    private final CurrencyParser currencyParser;

    @Override
    public String requestCurrencyString(Currency firstCurrency, Currency secondCurrency) {
        return currencyParser.parseCurrencyValues(firstCurrency, secondCurrency);
    }
}
