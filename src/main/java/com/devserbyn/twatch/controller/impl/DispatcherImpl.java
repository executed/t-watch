package com.devserbyn.twatch.controller.impl;

import com.devserbyn.twatch.controller.Dispatcher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
public class DispatcherImpl implements Dispatcher {

    @Override
    public Optional<BotApiMethod> handleUpdate(Update update) {
        //to be continued...
        return Optional.empty();
    }
}
