package com.devserbyn.twatch.controller.api.mainbot;

import com.devserbyn.twatch.service.answer.api.mainbot.CurrencyAPIRequester;
import com.devserbyn.twatch.service.answer.api.mainbot.JokeAPIRequester;
import com.devserbyn.twatch.service.parser.mainbot.Currency;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CommandController implements ApiController {

    private final JokeAPIRequester jokeRequester;
    private final CurrencyAPIRequester currencyRequester;

    @Override
    public BotApiMethod handle(Update update) {
        String command = update.getMessage().getText();

        switch (command) {
            case "/joke": {
                String response = jokeRequester.getRandomJoke().orElseThrow(RuntimeException::new);
                return BotAnswerUtil.wrapIntoApiMethod(response, update);
            }
            case "/start": {
                String response = "Welcome";
                return BotAnswerUtil.wrapIntoApiMethod(response, update);
            }
            case "/currency": {
                String response = currencyRequester.requestCurrencyString(Currency.USD, Currency.UAH);
                return BotAnswerUtil.wrapIntoApiMethod(response, update);
            }
            default: {
                return BotAnswerUtil.wrapIntoApiMethod("Command wasn't recognized", update);
            }
        }
    }
}
