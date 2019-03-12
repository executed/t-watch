package com.devserbyn.twatch.controller.impl;

import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.controller.RequestResolver;
import com.devserbyn.twatch.model.bot.BaseBot;
import com.devserbyn.twatch.service.DispatcherService;
import com.devserbyn.twatch.service.ExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DispatcherImpl implements Dispatcher {

    private final DispatcherService dispatcherService;
    private final ExceptionHandler exceptionHandler;

    @Override
    public Optional<BotApiMethod> handleUpdate(Update update, Class<? extends BaseBot> botClass) {
        RequestResolver requestResolver = dispatcherService.getBotRequestResolver(botClass);

        try {
            return requestResolver.resolveUpdate(update);
        } catch (Exception e) {
            return exceptionHandler.handleException(e, update, botClass);
        }
    }
}
