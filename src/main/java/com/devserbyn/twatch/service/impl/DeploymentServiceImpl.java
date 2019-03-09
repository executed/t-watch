package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySources ({
        @PropertySource ("classpath:deployment.properties")
})
public class DeploymentServiceImpl implements DeploymentService {

    private final Environment env;
    private final ApplicationBO applicationBO;

    /** Requests for page of current app to forbid deployment server snoozing process */
    @Scheduled (cron = "${deployment.preventScheduling.cronExp}")
    public void postponeSnoozeOnServer() throws IOException {
        if (applicationBO.isDevelopmentMode()) {
            return;
        }
        log.trace("Snoozing prevention job started");
        String contextPath = env.getProperty("deployment.contextPath");
        String pageLoadTimeoutStr = env.getProperty("deployment.preventScheduling.pageLoadTimeout");
        Document doc = Jsoup.connect(contextPath)
                            .timeout(Integer.valueOf(requireNonNull(pageLoadTimeoutStr)))
                            .get();
        if (doc != null) {
            log.trace("Snoozing prevention job succeeded");
        }
    }

    @Override
    public void startupBotByHand() {
	String url = applicationBO.isDevelopmentMode() ? env.getProperty("deployment.startupBotContextPath.develop")
						       : env.getProperty("deployment.startupBotContextPath.production")
        accessURL(url);
    }

    private void accessURL(String url){
        try {
            String pageLoadTimeoutStr = env.getProperty("deployment.preventScheduling.pageLoadTimeout");
            Jsoup.connect(url)
                 .timeout(Integer.valueOf(requireNonNull(pageLoadTimeoutStr)))
                 .ignoreContentType(true)
                 .get();
        } catch (IOException e) {
            log.error("Something went wrong while accessing URL {}", url, e);
        }
    }
}
