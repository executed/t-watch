package com.devserbyn.twatch.listener;

import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.service.ApplicationService;
import com.devserbyn.twatch.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ApplicationStartedEvent> {

    private final ApplicationService applicationService;
    private final ApplicationArguments applicationArguments;
    private final ApplicationBO applicationBO;
    private final DeploymentService deploymentService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        applicationService.resolveApplicationArgs(applicationArguments.getSourceArgs());
            deploymentService.startupBotByHand();
    }
}
