package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.model.EmailMessage;
import com.devserbyn.twatch.service.EmailSenderScheduledService;
import com.devserbyn.twatch.service.EmailService;
import com.devserbyn.twatch.utility.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource ("classpath:deployment.properties")
public class EmailSenderScheduledServiceImpl implements EmailSenderScheduledService {

    private final EmailUtil emailUtil;
    private final EmailService emailService;

    @Override
    @Scheduled(cron = "${deployment.sendDictionaryFile.cronExp}")
    public void sendDictionaryFile() {
        log.info("Sending email with dictionary data...");
        File attachment = emailUtil.getDictionaryAttachment().orElseThrow(RuntimeException::new);
        EmailMessage message = EmailMessage.builder().attachment(attachment)
                                                     .title("Dictionary file")
                                                     .content("Generated " + new Date())
                                                     .target("twatch.bot.report@gmail.com").build();
        emailService.sendEmail(message);
    }
}
