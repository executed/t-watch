package com.devserbyn.twatch.controller.bot;

import com.devserbyn.twatch.controller.FrontController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainBot extends BaseBot{

    private final FrontController<MainBot> controller;

    @Autowired
    protected MainBot() {
        super();
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
