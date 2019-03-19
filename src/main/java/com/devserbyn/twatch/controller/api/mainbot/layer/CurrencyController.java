package com.devserbyn.twatch.controller.api.mainbot.layer;

import com.devserbyn.twatch.constant.STR_CONST;
import com.devserbyn.twatch.controller.LayerController;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.model.mainbot.currency.CurrencyBO;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.service.answer.api.mainbot.CurrencyAPIRequester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyController implements LayerController {

    private final CurrencyBO currencyBO;
    private final CurrencyAPIRequester currencyRequester;
    private final BotAnswerService botAnswerService;

    @Override
    public String resolveUpdate(Update update) {
        if (update.getMessage().isCommand()) {
            return this.resolveCommand(update);
        } else if (update.getMessage().isUserMessage()) {
            return this.resolvePlainText(update);
        } else {
            return botAnswerService.lookForServiceResponse(STR_CONST.BOT_ANSWER_SERVICE_UNKNOWN, MainBot.class);
        }
    }

    private String resolveCommand(Update update) {
        return null;
    }

    private String resolvePlainText(Update update) {
        String text = update.getMessage().getText();

        if (currencyBO.isSettingMode()) {
            return currencyRequester.resolveCurrencySettingMode(update);
        }
        else {
            return botAnswerService.lookForServiceResponse(STR_CONST.BOT_ANSWER_SERVICE_UNKNOWN, MainBot.class);
        }
    }

}
