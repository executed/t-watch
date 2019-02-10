package com.devserbyn.twatch.controller.impl;

import com.devserbyn.twatch.annotation.Profiled;
import com.devserbyn.twatch.controller.Dispatcher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@Profiled
public class DispatcherImpl implements Dispatcher {

    @Override
    public Optional<BotApiMethod> handleUpdate(Update update) {
        //to be continued...
        SendMessage msg = new SendMessage();
        msg.setText("Some text");
        msg.setChatId(update.getMessage().getChatId());
        return Optional.of(msg);
    }
}
