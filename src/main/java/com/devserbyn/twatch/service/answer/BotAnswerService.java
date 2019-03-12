package com.devserbyn.twatch.service.answer;

import com.devserbyn.twatch.model.bot.BaseBot;

public interface BotAnswerService {

    String lookForAnswer(String message, Class<? extends BaseBot> botClass);

    void learnNewAnswer(String answer, Class<? extends BaseBot> botClass);

    String lookForServiceResponse(String key, Class<? extends BaseBot> botClass);
}
