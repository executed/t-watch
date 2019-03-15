package com.devserbyn.twatch.listener;

import com.devserbyn.twatch.service.BotRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShutdownApplicationEvent implements ApplicationListener<ContextStoppedEvent> {

    private final BotRegisterService botRegisterService;

    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        botRegisterService.startOnRemote();
    }
}
