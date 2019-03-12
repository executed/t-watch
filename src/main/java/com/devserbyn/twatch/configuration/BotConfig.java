package com.devserbyn.twatch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi getTelegramBotsApi() {
        return new TelegramBotsApi();
    }
}
