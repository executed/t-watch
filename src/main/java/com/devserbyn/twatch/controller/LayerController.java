package com.devserbyn.twatch.controller;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface LayerController {

    Optional<BotApiMethod> resolveUpdate(Update update);
}
