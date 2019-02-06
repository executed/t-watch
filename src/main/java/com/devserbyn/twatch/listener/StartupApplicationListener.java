package com.devserbyn.twatch.listener;

import com.devserbyn.twatch.service.BotRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ContextStartedEvent> {

    private final BotRegisterService botRegisterService;

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        log.info("Application started");
        ApiContextInitializer.init();
        botRegisterService.registerBots();
    }

}