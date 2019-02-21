package com.devserbyn.twatch.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface BotAnswerWriterService {

    void saveAnswer(String answerString) throws IOException;
}
