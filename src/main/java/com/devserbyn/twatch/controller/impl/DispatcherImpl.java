package com.devserbyn.twatch.controller.impl;

import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.service.answer.api.JokeAPIRequester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DispatcherImpl implements Dispatcher {

    private final BotAnswerService service;
    private final JokeAPIRequester jokeService;

    @Override
    public Optional<BotApiMethod> handleUpdate(Update update) {
        //to be continued...
        Message msg = update.getMessage();
        if (msg == null) {
            return Optional.empty();
        }
        String msgText = msg.getText();

        SendMessage sendMsg = new SendMessage();

        if (msgText.contains("dict")) {
            service.learnNewAnswer(msgText, MainBot.class);
        } else if(msgText.equals("/joke")) {
            sendMsg.setText(jokeService.getRandomJoke().orElseThrow(RuntimeException::new));
            sendMsg.setChatId(update.getMessage().getChatId());
            return Optional.of(sendMsg);
        }
        String answer = service.lookForAnswer(msgText, MainBot.class);
        sendMsg.setText(answer);
        sendMsg.setChatId(update.getMessage().getChatId());

        return Optional.of(sendMsg);
    }
}
