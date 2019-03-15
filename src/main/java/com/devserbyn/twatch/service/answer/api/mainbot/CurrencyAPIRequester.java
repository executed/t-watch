package com.devserbyn.twatch.service.answer.api.mainbot;

import com.devserbyn.twatch.service.parser.mainbot.Currency;

public interface CurrencyAPIRequester {

    String requestCurrencyString(Currency firstCurrency, Currency secondCurrency);
}
