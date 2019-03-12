package com.devserbyn.twatch.controller.api.mainbot;

import com.devserbyn.twatch.exception.TwatchException;
import com.devserbyn.twatch.service.answer.api.JokeAPIRequester;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CommandController implements ApiController {

    private final JokeAPIRequester jokeService;

    @Override
    public BotApiMethod handle(Update update) {
        String command = update.getMessage().getText();

        switch (command) {
            case "/joke": {
                String response = jokeService.getRandomJoke().orElseThrow(RuntimeException::new);
                return BotAnswerUtil.wrapIntoApiMethod(response, update);
            }
            case "/start": {
                String response = "Welcome";
                return BotAnswerUtil.wrapIntoApiMethod(response, update);
            }
            default: {
                return null;
            }
        }
    }
}
