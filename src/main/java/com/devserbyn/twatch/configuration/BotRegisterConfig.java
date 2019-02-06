package com.devserbyn.twatch.configuration;

import com.devserbyn.twatch.controller.bot.BaseBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BotRegisterConfig {

    @Bean
    BotsApiContext apiConfig() {
        BotsApiContext configBO = new BotsApiContext();

        configBO.setBotsApi(new TelegramBotsApi());

        List<TelegramLongPollingBot> bots = new ArrayList<>();
        bots.add(new BaseBot());
        configBO.setLongPollingBots(bots);

        return configBO;
    }
}
