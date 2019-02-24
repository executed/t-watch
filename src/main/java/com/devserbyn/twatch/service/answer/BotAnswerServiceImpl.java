package com.devserbyn.twatch.service.answer;

import com.devserbyn.twatch.constant.STR_CONST;
import com.devserbyn.twatch.model.bot.BaseBot;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.devserbyn.twatch.constant.STR_CONST.BOT_ANSWER_FILES_POSTFIX;

@RequiredArgsConstructor
@Service
@Slf4j
public class BotAnswerServiceImpl implements BotAnswerService{

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
}
