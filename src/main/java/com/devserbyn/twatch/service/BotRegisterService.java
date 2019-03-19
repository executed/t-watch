package com.devserbyn.twatch.service;

import com.devserbyn.twatch.model.bot.BaseBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public interface BotRegisterService {

    String getToken(String className);

    String getUsername(String className);

    void register(Class<? extends BaseBot> botClass) throws TelegramApiRequestException;

    void stop(Class<? extends BaseBot> botClass);

    void stopOnRemote();

    void startOnRemote();
}
