package com.devserbyn.twatch.service.scheduled;

import com.devserbyn.twatch.constant.MAIL_CONST;
import com.devserbyn.twatch.constant.PATH_CONST;
import com.devserbyn.twatch.constant.PROPERTY_CONST;
import com.devserbyn.twatch.model.EmailMessage;
import com.devserbyn.twatch.model.bo.BotAnswerBO;
import com.devserbyn.twatch.service.EmailService;
import com.devserbyn.twatch.utility.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySources ({
        @PropertySource (PATH_CONST.PROPERTY_DEPLOYMENT),
        @PropertySource (PATH_CONST.PROPERTY_MAIL)
})
public class DictionaryFileScheduleServiceImpl {

    private final Environment env;
    private final EmailUtil emailUtil;
    private final EmailService emailService;
    private final BotAnswerBO botAnswerBO;

    @Scheduled (cron = "${deployment.sendDictionaryFile.cronExp}")
    public void sendDictionaryFile() {
        if (!botAnswerBO.isDictionaryModified()) {
            return;
        }
        File attachment = emailUtil.getDictionaryAttachment().orElseThrow(RuntimeException::new);
        EmailMessage message = EmailMessage.builder().attachment(attachment)
                .title(MAIL_CONST.DICT_FILE_TITLE)
                .content(MAIL_CONST.DICT_FILE_CONTENT_PREFIX + new Date())
                .target(env.getProperty("email.report"))
                .comment(MAIL_CONST.DICT_FILE_COMMENT)
                .build();
        emailService.sendEmail(message);
    }
}
