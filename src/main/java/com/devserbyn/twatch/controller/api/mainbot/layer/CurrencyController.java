package com.devserbyn.twatch.controller.api.mainbot.layer;

import com.devserbyn.twatch.constant.STR_CONST;
import com.devserbyn.twatch.controller.LayerController;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.model.mainbot.currency.CurrencyBO;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.service.answer.api.mainbot.CurrencyAPIRequester;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyController implements LayerController {

    private final CurrencyBO currencyBO;
    private final CurrencyAPIRequester currencyRequester;
    private final BotAnswerService botAnswerService;

    @Override
    public Optional<BotApiMethod> resolveUpdate(Update update) {
        return this.resolvePlainText(update);
    }

    private Optional<BotApiMethod> resolvePlainText(Update update) {
        if (currencyBO.isSettingMode()) {
            return currencyRequester.resolveCurrencySettingMode(update);
        } else {
            String response =  botAnswerService.lookForServiceResponse(STR_CONST.BOT_ANSWER_SERVICE_UNKNOWN, MainBot.class);
            return Optional.of(BotAnswerUtil.wrapIntoApiMethod(response, update, true));
        }
    }

}
