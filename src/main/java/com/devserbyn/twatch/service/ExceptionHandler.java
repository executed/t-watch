package com.devserbyn.twatch.service;

import com.devserbyn.twatch.model.bot.BaseBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface ExceptionHandler {

    Optional<BotApiMethod> handleException(Exception e,
                                           Update update,
                                           Class<? extends BaseBot> botClass);
}
