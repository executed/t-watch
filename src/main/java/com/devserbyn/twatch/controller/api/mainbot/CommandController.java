package com.devserbyn.twatch.controller.api.mainbot;

import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.model.mainbot.User;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.service.answer.api.JokeAPIRequester;
import com.devserbyn.twatch.service.mainbot.UserService;
import com.devserbyn.twatch.service.mainbot.repository.UserRepositoryService;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import sun.applet.Main;

@Component
@RequiredArgsConstructor
public class CommandController implements ApiController {

    private final JokeAPIRequester jokeService;
    private final BotAnswerService botAnswerService;
    private final UserService userService;

    @Override
    public BotApiMethod handle(Update update) {
        String command = update.getMessage().getText();

        switch (command) {
            case "/joke": {
                String response = jokeService.getRandomJoke().orElseThrow(RuntimeException::new);
                return BotAnswerUtil.wrapIntoApiMethod(response, update, true);
            }
            case "/start": {
                String response = botAnswerService.lookForCommandResponse(command, MainBot.class);
                return BotAnswerUtil.wrapIntoApiMethod(response, update, true);
            }
            case "/commands": {
                String response = botAnswerService.lookForCommandResponse(command, MainBot.class);
                return BotAnswerUtil.wrapIntoApiMethod(response, update, true);
            }
            case "/register": {
                String response = userService.register(update);
                return BotAnswerUtil.wrapIntoApiMethod(response, update, true);
            }
            case "/removeme": {
                String response = userService.remove(update);
                return BotAnswerUtil.wrapIntoApiMethod(response, update, true);
            }
            default: {
                return BotAnswerUtil.wrapIntoApiMethod("Command wasn't recognized", update);
            }
        }
    }
}
