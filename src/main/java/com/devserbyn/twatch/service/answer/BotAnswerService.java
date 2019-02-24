package com.devserbyn.twatch.service.answer;

import com.devserbyn.twatch.model.bot.BaseBot;

public interface BotAnswerService {

    String lookForAnswer(String message, Class<? extends BaseBot> botClass);
}
