package com.devserbyn.twatch.controller.impl.resolver;

import com.devserbyn.twatch.controller.RequestResolver;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class TestBotRequestResolver implements RequestResolver {

    @Override
    public Optional<BotApiMethod> resolveUpdate(Update update) {
        return Optional.empty();
    }
}
