package com.devserbyn.twatch.service.parser.mainbot;

import com.devserbyn.twatch.model.mainbot.currency.Currency;

public interface CurrencyParser {

    String parseCurrencyValues(Currency firstCurrency, Currency secondCurrency);
}
