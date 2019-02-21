package com.devserbyn.twatch.controller.impl;

import com.devserbyn.twatch.annotation.Profiled;
import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.service.BotAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@Profiled
public class DispatcherImpl implements Dispatcher {

    @Autowired BotAnswersService service;

    @Override
    public Optional<BotApiMethod> handleUpdate(Update update) {
        //to be continued...
        SendMessage msg = new SendMessage();
        msg.setText(service.lookForAnswer(update.getMessage().getText()));
        msg.setChatId(update.getMessage().getChatId());
        return Optional.of(msg);
    }
}
