package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.constant.PROPERTY_CONST;
import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.BotRegisterService;
import com.devserbyn.twatch.service.DeploymentService;
import com.devserbyn.twatch.utility.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;

import static com.devserbyn.twatch.constant.PROPERTY_CONST.DEPLOYMENT_STARTUP_LOAD_TIMEOUT;
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
    private final WebUtil webUtil;
    private final BotRegisterService botRegisterService;

    /** Requests for page of current app to forbid deployment server snoozing process */
    @Scheduled (cron = "${deployment.preventScheduling.cronExp}")
    public void postponeSnoozeOnServer() {
        if (applicationBO.isDevelopmentMode()) {
            return;
        }
        log.trace("Snoozing prevention job started");

        String url = env.getProperty("deployment.contextPath");
        webUtil.accessURL(url);

        log.trace("Snoozing prevention job succeeded");
    }

    @Override
    public void startupBotByHand() {
        try {
            botRegisterService.register(MainBot.class);
        } catch (TelegramApiRequestException e) {
            log.error("Something went wrong while starting bot by hand", e);
        }
    }
}
