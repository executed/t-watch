package com.devserbyn.twatch.configuration;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class BotsApiContext {

    private TelegramBotsApi botsApi;
    private List<TelegramLongPollingBot> longPollingBots = new ArrayList<>();
    private List<TelegramLongPollingBot> webHookBots = new ArrayList<>();
}
