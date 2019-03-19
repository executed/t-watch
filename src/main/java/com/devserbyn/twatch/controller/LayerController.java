package com.devserbyn.twatch.controller;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface LayerController {

    String resolveUpdate(Update update);
}
