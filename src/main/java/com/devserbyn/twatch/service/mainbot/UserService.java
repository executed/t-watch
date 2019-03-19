package com.devserbyn.twatch.service.mainbot;

import com.devserbyn.twatch.model.mainbot.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserService {

    String register(Update update);

    String remove(Update update);

    boolean checkIfUserRegistered(String username);
}
