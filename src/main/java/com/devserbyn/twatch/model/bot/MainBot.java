package com.devserbyn.twatch.model.bot;

import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.service.BotRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainBot extends BaseBot{

    @Autowired
    protected MainBot(Dispatcher dispatcher, BotRegisterService registerService) {
        super(dispatcher, registerService);
    }
}
