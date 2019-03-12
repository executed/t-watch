package com.devserbyn.twatch.controller.api.mainbot;

import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlainTextController implements ApiController {

    private final BotAnswerService botAnswerService;

    @Override
    public BotApiMethod handle(Update update) {
        String messageText = update.getMessage().getText();
        String responseText = botAnswerService.lookForAnswer(messageText, MainBot.class);

        return BotAnswerUtil.wrapIntoApiMethod(responseText, update);
    }
}
