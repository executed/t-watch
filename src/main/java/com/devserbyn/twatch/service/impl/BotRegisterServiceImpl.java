package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.constant.PROPERTY_CONST;
import com.devserbyn.twatch.exception.TwatchException;
import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.model.bot.BaseBot;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.BotRegisterService;
import com.devserbyn.twatch.utility.PropertyUtil;
import com.devserbyn.twatch.utility.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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

        if (applicationBO.getRegisteredBots().containsKey(bot)) {
            throw new TwatchException(String.format("%s was already registered", botClass.getSimpleName()));
        }
        BotSession botSession = context.getBean(TelegramBotsApi.class).registerBot(bot);
        applicationBO.getRegisteredBots().put(bot, botSession);

        log.info("Bot {} was registered", MainBot.class);
    }

    @Override
    public void stop(Class<? extends BaseBot> botClass) {
        ApplicationBO applicationBO = context.getBean(ApplicationBO.class);
        BaseBot bot = context.getBean(botClass);

        BotSession botSession = applicationBO.getRegisteredBots().get(bot);
        if (botSession == null) {
            throw new TwatchException(String.format("%s isn't online. Nothing was affected", botClass.getSimpleName()));
        }
        botSession.stop();
        applicationBO.getRegisteredBots().remove(bot);

        log.info("Bot {} was stopped", MainBot.class);
    }

    @Override
    public void startOnRemote() {
        String url = propertyUtil.getProperty(PROPERTY_CONST.DEPLOYMENT_BOT_START_DEV_HOST);
        context.getBean(WebUtil.class).accessURL(url);

        log.info("Bot {} on remote {} was started", MainBot.class, url);
    }

    @Override
    public void stopOnRemote() {
        String url = propertyUtil.getProperty(PROPERTY_CONST.DEPLOYMENT_BOT_STOP_DEV_HOST);
        context.getBean(WebUtil.class).accessURL(url);

        log.info("Bot {} on remote {} was started", MainBot.class, url);
    }
}
