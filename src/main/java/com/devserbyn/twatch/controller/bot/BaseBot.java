package com.devserbyn.twatch.controller.bot;

import com.devserbyn.twatch.controller.FrontController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class BaseBot extends TelegramLongPollingBot {

    protected FrontController controller;

    protected BaseBot(FrontController frontController) {
        this.controller = frontController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        controller.handleUpdate(update, this);
    }

    public void sendMessage(String msg) {
        System.out.println("SENDING MSG: " + msg);
    }

}