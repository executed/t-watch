package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
@PropertySource ("classpath:deployment.properties")
public class MainBotAdminServiceImpl implements AdminService {

    private final MainBot bot;
    private static int minutesOfRuntime = 0;

    @Scheduled(cron = "${deployment.activityStatusMessage.cronExp}")
    public void sendMessageToAdmin() {
        SendMessage message = new SendMessage();
        message.setChatId(523876506L);
        message.setText("" + (minutesOfRuntime + 30));
        bot.sendResponse(message);
    }
}
