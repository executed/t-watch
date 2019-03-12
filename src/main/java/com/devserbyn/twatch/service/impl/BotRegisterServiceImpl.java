package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.constant.PROPERTY_CONST;
import com.devserbyn.twatch.exception.TwatchException;
import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.model.bot.BaseBot;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.BotRegisterService;
import com.devserbyn.twatch.utility.PropertyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotRegisterServiceImpl implements BotRegisterService {

    private final ApplicationContext context;
    private final PropertyUtil propertyUtil;

    @Override
    public String getToken(String className) {
        return propertyUtil.getBotPropertyByFormat(className, PROPERTY_CONST.BOT_TOKEN_KEY_FORMAT);
    }

    @Override
    public String getUsername(String className) {
        return propertyUtil.getBotPropertyByFormat(className, PROPERTY_CONST.BOT_USERNAME_KEY_FORMAT);
    }

    @Override
    public void register(Class<? extends BaseBot> botClass) throws TelegramApiRequestException {
        ApplicationBO applicationBO = context.getBean(ApplicationBO.class);
        BaseBot bot = context.getBean(botClass);

        if (applicationBO.getRegisteredBots().contains(bot)) {
            throw new TwatchException(String.format("%s was already registered", botClass.getSimpleName()));
        }
        context.getBean(TelegramBotsApi.class).registerBot(bot);
        applicationBO.getRegisteredBots().add(bot);
        log.info("Bot {} was registered", MainBot.class);
    }
}
