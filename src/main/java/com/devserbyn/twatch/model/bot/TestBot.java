package com.devserbyn.twatch.model.bot;

import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.service.BotRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestBot extends BaseBot{

    @Autowired
    protected TestBot(Dispatcher dispatcher, BotRegisterService registerService) {
        super(dispatcher, registerService);
    }
}
