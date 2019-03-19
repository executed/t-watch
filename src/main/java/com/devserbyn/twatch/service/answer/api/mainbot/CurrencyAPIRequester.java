package com.devserbyn.twatch.service.answer.api.mainbot;

import com.devserbyn.twatch.model.mainbot.currency.Currency;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface CurrencyAPIRequester {

    String requestCurrencyString(Update update, Currency firstCurrency, Currency secondCurrency);

    Optional<BotApiMethod> resolveCurrencySettingMode(Update update);

    String enableSettingsMode(Update update);
}
