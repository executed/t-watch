package com.devserbyn.twatch.service.mainbot.impl;

import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.model.mainbot.User;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.service.mainbot.UserService;
import com.devserbyn.twatch.service.mainbot.repository.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.devserbyn.twatch.constant.STR_CONST.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryService userRepoService;
    private final BotAnswerService botAnswerService;

    @Override
    public String register(Update update) {
        User newUser = new User();
        newUser.setChatId(update.getMessage().getChatId());
        newUser.setUsername(update.getMessage().getFrom().getUserName());
        try {
            userRepoService.save(newUser).orElseThrow(RuntimeException::new);

            return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_REGISTER_SUCCESS, MainBot.class);
        } catch (Exception e) {
            return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_REGISTER_FAIL, MainBot.class);
        }
    }

    @Override
    public String remove(Update update) {
        try {
            userRepoService.deleteByChatId(update.getMessage().getChatId());

            return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_DEREGISTER_SUCCESS, MainBot.class);
        } catch (Exception e) {
            return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_DEREGISTER_FAIL, MainBot.class);
        }
    }

    @Override
    public boolean checkIfUserRegistered(String username) {
        return userRepoService.findByUsername(username).isPresent();
    }
}
