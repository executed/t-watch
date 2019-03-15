package com.devserbyn.twatch.controller.impl.resolver;

import com.devserbyn.twatch.controller.RequestResolver;
import com.devserbyn.twatch.controller.api.mainbot.BaseApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainBotRequestResolver implements RequestResolver {

    private final BaseApiController apiController;

    @Override
    public Optional<BotApiMethod> resolveUpdate(Update update) {
        return apiController.handle(update);
    }
}
