package com.devserbyn.twatch.service.answer.api.mainbot;

import com.devserbyn.twatch.service.parser.mainbot.Currency;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CurrencyAPIRequester {

    String requestCurrencyString(Update update, Currency firstCurrency, Currency secondCurrency);
}
