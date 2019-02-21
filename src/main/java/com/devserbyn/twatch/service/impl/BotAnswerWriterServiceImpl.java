package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.service.BotAnswerWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class BotAnswerWriterServiceImpl implements BotAnswerWriterService {

    @Autowired
    Environment environment;

    @Override
    public void saveAnswer(String answerString) throws IOException {
        Properties prop = new Properties();

        String[] property = answerString.split(" = ");
        if (environment.getProperty(property[0]).equals(property[1])) {
            return;
        }
        prop.setProperty(property[0], property[1]);
        prop.store(new FileOutputStream(
                new File("classpath:string/bot/answers/main_bot_answers.properties")),
                 "");
    }
}
