package com.devserbyn.twatch.controller.bot;

import com.devserbyn.twatch.controller.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainBot extends BaseBot{

    private final Dispatcher controller;

    @Autowired
    protected MainBot(Dispatcher controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
