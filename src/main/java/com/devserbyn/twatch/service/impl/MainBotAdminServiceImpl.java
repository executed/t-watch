package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.constant.PATH_CONST;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
@PropertySource (PATH_CONST.PROPERTY_DEPLOYMENT)
public class MainBotAdminServiceImpl implements AdminService {

    private final Environment env;
    private final MainBot bot;
    private int minutesOfRuntime = Integer.parseInt(env.getProperty("deployment.activityStatusMessage.timeDiff"));

    @Scheduled(cron = "${deployment.activityStatusMessage.cronExp}")
    public void sendMessageToAdmin() {
        SendMessage message = new SendMessage();
        message.setChatId(env.getProperty("deployment.admin.chatId"));
        message.setText("Application running minutes: " + minutesOfRuntime);
        bot.sendResponse(message);
        minutesOfRuntime += 60;
    }
}
