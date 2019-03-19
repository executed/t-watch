package com.devserbyn.twatch.service.answer;

import com.devserbyn.twatch.model.bot.BaseBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface BotAnswerService {

    String lookForAnswer(String message, Class<? extends BaseBot> botClass);

    void learnNewAnswer(String answer, Class<? extends BaseBot> botClass);

    String lookForServiceResponse(String key, Class<? extends BaseBot> botClass);

    String lookForCommandResponse(String key, Class<? extends BaseBot> botClass);

    String getNotRegisteredResponse(Class<? extends BaseBot> botClass);

    Optional<BotApiMethod> getNotRegisteredResponse(Update update, Class<? extends BaseBot> botClass);
}
