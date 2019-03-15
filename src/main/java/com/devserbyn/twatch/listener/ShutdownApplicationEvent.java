package com.devserbyn.twatch.listener;

import com.devserbyn.twatch.service.ApplicationService;
import com.devserbyn.twatch.service.BotRegisterService;
import com.devserbyn.twatch.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

public class ShutdownApplicationEvent {

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ContextStoppedEvent> {

    private final DeploymentService deploymentService;
    private final BotRegisterService botRegisterService;

    @Override
    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        botRegisterService.startOnRemote();
    }
}

}
