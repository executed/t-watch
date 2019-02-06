package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.configuration.BotsApiContext;
import com.devserbyn.twatch.service.BotRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotRegisterServiceImpl implements BotRegisterService {

    private final BotsApiContext botsApiContext;

    @Override
    public void registerBots() {
        TelegramBotsApi botsApi = botsApiContext.getBotsApi();
        botsApiContext.getLongPollingBots().forEach(bot -> {
            try {
                botsApi.registerBot(bot);
                log.warn(String.format("Bot %s registered", bot.getBotUsername()));
            } catch (TelegramApiRequestException e) {
                log.error("Error while bot registering", e);
            }
        });
    }
}
