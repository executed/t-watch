package com.devserbyn.twatch.controller.impl.resolver;

import com.devserbyn.twatch.controller.RequestResolver;
import com.devserbyn.twatch.controller.api.mainbot.BaseApiController;
import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.utility.BotAnswerUtil;
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
        // TODO: Remake on botapi convertion inside layer resolveUpdate
        if (applicationBO.getActiveLayerController() != null) {
            String response = applicationBO.getActiveLayerController().resolveUpdate(update);
            return Optional.of(BotAnswerUtil.wrapIntoApiMethod(response, update, true));
        }
        return apiController.handle(update);
    }
}
