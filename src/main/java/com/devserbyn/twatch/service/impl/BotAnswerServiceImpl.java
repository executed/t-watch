package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.service.BotAnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@PropertySources(
        @PropertySource ("classpath:string/bot/answers/main_bot_answers.properties")
)
@Service
public class BotAnswerServiceImpl implements BotAnswersService {

    private final Environment env;

    @Override
    public String lookForAnswer(String message) {
        String answersString = env.getProperty(message.toUpperCase());
        if (answersString == null) {
            return "default answer";
        }
        String[] answers = answersString.split("|");
        return answers[new Random().nextInt(answers.length)];
    }
}
