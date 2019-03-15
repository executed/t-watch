package com.devserbyn.twatch.service;

import com.devserbyn.twatch.controller.RequestResolver;
import com.devserbyn.twatch.model.bot.BaseBot;

public interface DispatcherService {

    RequestResolver getBotRequestResolver(Class<? extends BaseBot> botClass);
}
