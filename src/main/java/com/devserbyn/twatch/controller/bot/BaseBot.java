package com.devserbyn.twatch.controller.bot;

import com.devserbyn.twatch.controller.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
public abstract class BaseBot extends TelegramLongPollingBot {

    protected Dispatcher dispatcher;

    protected BaseBot(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Optional<BotApiMethod> response = dispatcher.handleUpdate(update);
        response.ifPresent(this::sendResponse);
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
