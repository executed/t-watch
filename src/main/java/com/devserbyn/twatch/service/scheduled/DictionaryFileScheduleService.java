package com.devserbyn.twatch.service.scheduled;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.Scheduled;

@PropertySources ({
        @PropertySource ("classpath:deployment.properties")
})
public interface DictionaryFileScheduleService {

    @Scheduled(cron = "${deployment.sendDictionaryFile.cronExp}")
    void sendDictionaryFile();
}
