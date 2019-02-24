package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:deployment.properties")
public class DeploymentServiceImpl implements DeploymentService {

    private final Environment env;

    /** Requests for page of current app to forbid deployment server snoozing process */
    @Override
    //@Scheduled(cron = "${deployment.activityStatusMessage.cronExp}")
    public void postponeSnoozeOnServer() throws IOException {
        System.out.println("Start postpone snoozing prevent");
        String contextPath = env.getProperty("deployment.contextPath");
        String pageLoadTimeoutStr = env.getProperty("deployment.preventScheduling.pageLoadTimeout");
        Document doc = Jsoup.connect(contextPath)
                            .timeout(Integer.valueOf(requireNonNull(pageLoadTimeoutStr)))
                            .get();
        if (doc == null) {
            System.out.println("Failed: app was already snoozed.");
        } else {
            System.out.println("OK: snoozing of app prevented");
        }
    }
}
