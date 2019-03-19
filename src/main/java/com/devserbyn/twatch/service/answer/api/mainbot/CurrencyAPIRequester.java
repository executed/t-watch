package com.devserbyn.twatch.service.answer.api.mainbot;

import com.devserbyn.twatch.model.mainbot.currency.Currency;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CurrencyAPIRequester {

    String requestCurrencyString(Update update, Currency firstCurrency, Currency secondCurrency);

    String resolveCurrencySettingMode(Update update);

    String enableSettingsMode();
}
