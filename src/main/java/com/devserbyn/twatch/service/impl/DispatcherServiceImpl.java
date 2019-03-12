package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.constant.STR_CONST;
import com.devserbyn.twatch.controller.RequestResolver;
import com.devserbyn.twatch.exception.TwatchException;
import com.devserbyn.twatch.model.bot.BaseBot;
import com.devserbyn.twatch.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatcherServiceImpl implements DispatcherService {

    private final ApplicationContext context;

    @Override
    public RequestResolver getBotRequestResolver(Class<? extends BaseBot> botClass) {
        String resolverName = formatResolverName(botClass);
        if (!context.containsBean(resolverName)) {
            throw new TwatchException(String.format("No request resolver for bot %s available", botClass.getSimpleName()));
        }

        return (RequestResolver) context.getBean(resolverName);
    }

    private static String formatResolverName(Class<? extends BaseBot> botClass) {
        String botClassSimpleName = botClass.getSimpleName();

        return botClassSimpleName.toLowerCase().charAt(0) +
              botClassSimpleName.substring(1) +
              STR_CONST.BOT_RESOLVER_NAME_POSTFIX;
    }
}
