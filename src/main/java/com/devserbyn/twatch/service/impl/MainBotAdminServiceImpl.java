package com.devserbyn.twatch.service.impl;

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
@PropertySource ("classpath:deployment.properties")
public class MainBotAdminServiceImpl implements AdminService {

    private final Environment env;
    private final MainBot bot;
    private static int minutesOfRuntime = 0;

    @Scheduled(cron = "${deployment.activityStatusMessage.cronExp}")
    public void sendMessageToAdmin() {
        SendMessage message = new SendMessage();
        message.setChatId(env.getProperty("deployment.admin.chatId"));
        message.setText("Application running minutes: " + minutesOfRuntime);
        minutesOfRuntime += 60;
        bot.sendResponse(message);
    }
}
