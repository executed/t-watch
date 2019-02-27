package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.model.EmailMessage;
import com.devserbyn.twatch.model.bo.BotAnswerBO;
import com.devserbyn.twatch.service.DeploymentService;
import com.devserbyn.twatch.service.EmailService;
import com.devserbyn.twatch.utility.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:deployment.properties")
public class DeploymentServiceImpl implements DeploymentService {

    private final Environment env;
    private final EmailUtil emailUtil;
    private final EmailService emailService;
    private final BotAnswerBO botAnswerBO;

    /** Requests for page of current app to forbid deployment server snoozing process */
    @Override
    @Scheduled(cron = "${deployment.preventScheduling.cronExp}")
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

    @Scheduled(cron = "${deployment.sendDictionaryFile.cronExp}")
    public void sendDictionaryFile() {
        if (!botAnswerBO.isDictionaryModified()) {
            return;
        }
        log.info("Sending email with dictionary data...");
        File attachment = emailUtil.getDictionaryAttachment().orElseThrow(RuntimeException::new);
        EmailMessage message = EmailMessage.builder().attachment(attachment)
                .title("Dictionary file")
                .content("Generated " + new Date())
                .target("twatch.bot.report@gmail.com").build();
        emailService.sendEmail(message);
    }
}
