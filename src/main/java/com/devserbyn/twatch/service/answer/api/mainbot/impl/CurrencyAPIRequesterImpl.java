package com.devserbyn.twatch.service.answer.api.mainbot.impl;

import com.devserbyn.twatch.constant.STR_CONST;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.service.answer.api.mainbot.CurrencyAPIRequester;
import com.devserbyn.twatch.service.parser.mainbot.Currency;
import com.devserbyn.twatch.service.parser.mainbot.CurrencyParser;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import com.devserbyn.twatch.utility.mainbot.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.devserbyn.twatch.constant.STR_CONST.BOT_ANSWER_SERVICE_NOT_REG;

@Service
@RequiredArgsConstructor
public class CurrencyAPIRequesterImpl implements CurrencyAPIRequester {

    private final CurrencyParser currencyParser;
    private final BotAnswerService botAnswerService;
    private final UserUtil userUtil;

    @Override
    public String requestCurrencyString(Update update, Currency firstCurrency, Currency secondCurrency) {
        if (!userUtil.isRegistered(update)) {
            return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_NOT_REG, MainBot.class);
        }
        return currencyParser.parseCurrencyValues(firstCurrency, secondCurrency);
    }
}
