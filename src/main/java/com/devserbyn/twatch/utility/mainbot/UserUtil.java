package com.devserbyn.twatch.utility.mainbot;

import com.devserbyn.twatch.service.mainbot.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUtil {

    private final UserService userService;

    public boolean isRegistered(Update update) {
        String username = update.getMessage().getFrom().getUserName();
        return userService.checkIfUserRegistered(username);
    }
}
