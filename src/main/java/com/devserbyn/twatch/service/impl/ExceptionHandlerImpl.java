package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.exception.TwatchException;
import com.devserbyn.twatch.model.bot.BaseBot;
import com.devserbyn.twatch.service.ExceptionHandler;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlerImpl implements ExceptionHandler {

    private final BotAnswerService answerService;

    @Override
    public Optional<BotApiMethod> handleException(Exception e,
                                                  Update update,
                                                  Class<? extends BaseBot> botClass) {
        return this.resolveException(e, update, botClass);
    }

    private Optional<BotApiMethod> resolveException(Exception e,
                                                    Update update,
                                                    Class<? extends BaseBot> botClass) {
        if (e instanceof TwatchException) {
            String responseMsg = answerService.lookForServiceResponse(e.getMessage(), botClass);
            log.error("Twatch exception occurred", e);

            return generateResponseWithMsg(update, responseMsg);
        } else {
            String responseMsg = answerService.lookForServiceResponse(e.getMessage(), botClass);
            log.error("Unknown exception occurred", e);

            return generateResponseWithMsg(update, responseMsg);
        }
    }

    private static Optional<BotApiMethod> generateResponseWithMsg(Update update, String msg) {
        BotApiMethod result = new SendMessage(update.getMessage().getChatId(), msg);

        return Optional.of(result);
    }
}
