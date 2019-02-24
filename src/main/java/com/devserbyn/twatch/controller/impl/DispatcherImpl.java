package com.devserbyn.twatch.controller.impl;

import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DispatcherImpl implements Dispatcher {

    private final BotAnswerService service;

    @Override
    public Optional<BotApiMethod> handleUpdate(Update update) {
        //to be continued...
        String incomeMessage = update.getMessage().getText();

        SendMessage msg = new SendMessage();

        if (incomeMessage.contains("dict")) {
            service.learnNewAnswer(incomeMessage, MainBot.class);
        }
        String answer = service.lookForAnswer(incomeMessage, MainBot.class);
        msg.setText(answer);
        msg.setChatId(update.getMessage().getChatId());

        return Optional.of(msg);
    }
}
