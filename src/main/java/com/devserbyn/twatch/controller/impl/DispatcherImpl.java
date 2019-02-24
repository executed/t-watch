package com.devserbyn.twatch.controller.impl;

import com.devserbyn.twatch.controller.Dispatcher;
import com.devserbyn.twatch.service.BotAnswerWriterService;
import com.devserbyn.twatch.service.BotAnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DispatcherImpl implements Dispatcher {

    private final BotAnswersService service;
    private final BotAnswerWriterService answerWriterService;

    @Override
    public Optional<BotApiMethod> handleUpdate(Update update) {
        //to be continued...
        SendMessage msg = new SendMessage();
        /*if (msg.getText().contains("dict")) {
            String newDictPropertyStr = msg.getText().substring(msg.getText().indexOf("dict"));
            try {
                answerWriterService.saveAnswer(newDictPropertyStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        msg.setText(service.lookForAnswer(update.getMessage().getText()));
        msg.setChatId(update.getMessage().getChatId());
        return Optional.of(msg);
    }
}
