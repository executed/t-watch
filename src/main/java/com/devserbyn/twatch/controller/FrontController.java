package com.devserbyn.twatch.controller;

import com.devserbyn.twatch.controller.bot.BaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class FrontController<Bot extends BaseBot> {

    public abstract void handleUpdate(Update update, Bot invokerBot);
}
