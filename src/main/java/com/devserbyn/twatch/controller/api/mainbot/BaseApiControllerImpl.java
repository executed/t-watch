package com.devserbyn.twatch.controller.api.mainbot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BaseApiControllerImpl implements BaseApiController {

    private final CommandController commandController;
    private final PlainTextController plainTextController;

    public Optional<BotApiMethod> handle(Update update) {
        Message message = update.getMessage();
        if (message.isCommand()) {
            return this.handleCommand(update);
        } else if (message.hasText()) {
            return this.handlePlainText(update);
        } else {
            return Optional.empty();
        }
    }

    private Optional<BotApiMethod> handlePlainText(Update update) {
        return Optional.of(plainTextController.handle(update));
    }

    private Optional<BotApiMethod> handleCommand(Update update) {
        return Optional.of(commandController.handle(update));
    }
}
