package com.devserbyn.twatch.listener;

import com.devserbyn.twatch.service.BotRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ApplicationContextInitializedEvent> {

    private final BotRegisterService botRegisterService;


    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent applicationContextInitializedEvent) {
        log.info("Application started");
        ApiContextInitializer.init();
        botRegisterService.registerBots();
    }
}
