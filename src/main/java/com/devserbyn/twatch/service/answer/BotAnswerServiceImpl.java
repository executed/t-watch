package com.devserbyn.twatch.service.answer;

import com.devserbyn.twatch.constant.STR_CONST;
import com.devserbyn.twatch.model.bo.BotAnswerBO;
import com.devserbyn.twatch.model.bot.BaseBot;
import com.devserbyn.twatch.service.scheduled.DictionaryFileScheduleServiceImpl;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.devserbyn.twatch.constant.STR_CONST.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class BotAnswerServiceImpl implements BotAnswerService{

    private final BotAnswerBO botAnswerBO;
    private final DictionaryFileScheduleServiceImpl fileScheduleService;

    @Override
    public String lookForAnswer(String message, Class<? extends BaseBot> botClass) {
        String fileWithAnswersPath = STR_CONST.BOT_ANSWER_FILES_PREFIX
                                     + botClass.getSimpleName().toLowerCase()
                                     + BOT_ANSWER_FILES_POSTFIX;
        String key = message.toUpperCase();
        try {
            return BotAnswerUtil.processRandomAnswer(fileWithAnswersPath, key);
        } catch (IOException e) {
            log.error("Problem while processing random answer", e);
            return STR_CONST.BOT_ANSWER_ERROR;
        }
    }

    @Override
    public void learnNewAnswer(String message, Class<? extends BaseBot> botClass) {
        String fileWithAnswersPath = STR_CONST.BOT_ANSWER_FILES_PREFIX
                                    + botClass.getSimpleName().toLowerCase()
                                    + BOT_ANSWER_FILES_POSTFIX;
        String answerLine = message.substring(message.indexOf(":") + 2);
        try {
            BotAnswerUtil.addNewBotAnswer(fileWithAnswersPath, answerLine);
            botAnswerBO.setDictionaryModified(true);
            // This is horrible to sort file after each answer learning
            // Some type of learning session must be implemented
            // Only after session closing file must be sorted
            // Notice that sorting is required because answers at the end of file are not accessible
            // cause of answer search algorithm specifics
            fileScheduleService.sortDictionaryFile();
        } catch (IOException e) {
            log.error("Something wrong while learning new bot answer", e);
        }
    }

    @Override
    public String lookForServiceResponse(String key, Class<? extends BaseBot> botClass) {
        String fileWithAnswersPath = STR_CONST.BOT_SERVICE_RESPONSE_FILES_PREFIX
                + botClass.getSimpleName().toLowerCase()
                + BOT_SERVICE_RESPONSE_POSTFIX;
        String formattedKey = key.toUpperCase();
        try {
            return BotAnswerUtil.processRandomAnswer(fileWithAnswersPath, formattedKey);
        } catch (IOException e) {
            log.error("Problem while processing service response", e);
            return STR_CONST.BOT_ANSWER_ERROR;
        }
    }

    @Override
    public String lookForCommandResponse(String key, Class<? extends BaseBot> botClass) {
        String fileWithAnswersPath = STR_CONST.BOT_COMMANDS_RESPONSE_FILES_PREFIX
                + botClass.getSimpleName().toLowerCase()
                + BOT_COMMANDS_RESPONSE_POSTFIX;
        String formattedKey = key.toUpperCase();
        try {
            return BotAnswerUtil.processRandomAnswer(fileWithAnswersPath, formattedKey);
        } catch (IOException e) {
            log.error("Problem while processing command response", e);
            return STR_CONST.BOT_ANSWER_ERROR;
        }
    }
}
