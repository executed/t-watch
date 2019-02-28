package com.devserbyn.twatch.service.scheduled.impl;

import com.devserbyn.twatch.model.EmailMessage;
import com.devserbyn.twatch.model.bo.BotAnswerBO;
import com.devserbyn.twatch.service.EmailService;
import com.devserbyn.twatch.service.scheduled.DictionaryFileScheduleService;
import com.devserbyn.twatch.utility.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySources ({
        @PropertySource ("classpath:deployment.properties"),
        @PropertySource("classpath:mail.properties")
})
public class DictionaryFileScheduleServiceImpl implements DictionaryFileScheduleService {

    private final Environment env;
    private final EmailUtil emailUtil;
    private final EmailService emailService;
    private final BotAnswerBO botAnswerBO;

    @Override
    public void sendDictionaryFile() {
        if (!botAnswerBO.isDictionaryModified()) {
            return;
        }
        log.info("Sending email with dictionary data...");
        File attachment = emailUtil.getDictionaryAttachment().orElseThrow(RuntimeException::new);
        EmailMessage message = EmailMessage.builder().attachment(attachment)
                .title("Dictionary file")
                .content("Generated " + new Date())
                .target(env.getProperty("email.report"))
                .build();
        emailService.sendEmail(message);
    }
}
