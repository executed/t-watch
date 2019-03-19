package com.devserbyn.twatch.controller.impl.resolver;

import com.devserbyn.twatch.controller.RequestResolver;
import com.devserbyn.twatch.controller.api.mainbot.BaseApiController;
import com.devserbyn.twatch.model.bo.ApplicationBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainBotRequestResolver implements RequestResolver {

    private final BaseApiController apiController;
    private final ApplicationBO applicationBO;

    @Override
    public Optional<BotApiMethod> resolveUpdate(Update update) {

        if (applicationBO.getActiveLayerController() != null) {
            return this.resolveWithLayerController(update);
        } else {
            return apiController.handle(update);
        }
    }

    private Optional<BotApiMethod> resolveWithLayerController(Update update) {
        return applicationBO.getActiveLayerController().resolveUpdate(update);
    }
}
