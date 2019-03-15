package com.devserbyn.twatch.model.bot;

import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.service.BotRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
public abstract class BaseBot extends TelegramLongPollingBot {

    private Dispatcher dispatcher;
    private BotRegisterService registerService;

    protected BaseBot(Dispatcher dispatcher, BotRegisterService registerService) {
        this.dispatcher = dispatcher;
        this.registerService = registerService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Optional<BotApiMethod> response = dispatcher.handleUpdate(update, this.getClass());
        response.ifPresent(this::sendResponse);
    }

    @Override
    public String getBotUsername() {
        return registerService.getUsername(this.getClass().getSimpleName());
    }

    @Override
    public String getBotToken() {
        return registerService.getToken(this.getClass().getSimpleName());
    }

    public void sendResponse(BotApiMethod msg) {
        try {
            // getting real impl of apimethod
            this.execute(msg);
        } catch (TelegramApiException e) {
            log.error("Error sending message", e);
        }
    }
}
