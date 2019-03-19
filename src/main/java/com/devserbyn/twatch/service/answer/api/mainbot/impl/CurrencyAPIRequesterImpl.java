package com.devserbyn.twatch.service.answer.api.mainbot.impl;

import com.devserbyn.twatch.constant.STR_CONST;
import com.devserbyn.twatch.controller.api.mainbot.layer.CurrencyController;
import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.model.mainbot.User;
import com.devserbyn.twatch.model.mainbot.currency.CurrencyBO;
import com.devserbyn.twatch.model.mainbot.currency.CurrencySettings;
import com.devserbyn.twatch.service.answer.BotAnswerService;
import com.devserbyn.twatch.service.answer.api.mainbot.CurrencyAPIRequester;
import com.devserbyn.twatch.model.mainbot.currency.Currency;
import com.devserbyn.twatch.service.mainbot.UserService;
import com.devserbyn.twatch.service.mainbot.repository.CurrencySettingsRepositoryService;
import com.devserbyn.twatch.service.mainbot.repository.UserRepositoryService;
import com.devserbyn.twatch.service.parser.mainbot.CurrencyParser;
import com.devserbyn.twatch.utility.BotAnswerUtil;
import com.devserbyn.twatch.utility.mainbot.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.devserbyn.twatch.constant.STR_CONST.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyAPIRequesterImpl implements CurrencyAPIRequester {

    private final CurrencyParser currencyParser;
    private final BotAnswerService botAnswerService;
    private final UserUtil userUtil;
    private final CurrencyBO currencyBO;
    private final CurrencySettingsRepositoryService currencySettingsRepositoryService;
    private final UserRepositoryService userRepoService;
    private final ApplicationBO applicationBO;
    private final ApplicationContext context;

    @Override
    public String requestCurrencyString(Update update, Currency firstCurrency, Currency secondCurrency) {
        if (!userUtil.isRegistered(update)) {
            return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_NOT_REG, MainBot.class);
        }
        return currencyParser.parseCurrencyValues(firstCurrency, secondCurrency);
    }

    @Override
    public String enableSettingsMode() {
        currencyBO.setSettingMode(true);
        applicationBO.setActiveLayerController(context.getBean(CurrencyController.class));
        return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_CUR_SETTS_START, MainBot.class);
    }

    @Override
    public String resolveCurrencySettingMode(Update update) {
        if (!userUtil.isRegistered(update)) {
            return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_NOT_REG, MainBot.class);
        }
        String messageText = update.getMessage().getText();
        String username = update.getMessage().getFrom().getUserName();

        if (!currencyBO.isFirstCurrencySet()) {
            try {
                Currency firstCurrency = Currency.valueOf(messageText);
                CurrencySettings settings = new CurrencySettings();
                settings.setFirstCurrency(firstCurrency);

                currencySettingsRepositoryService.saveByUsername(settings, username);
                currencyBO.setFirstCurrencySet(true);

                return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_CURRENCY_SETS_FIRST_CUR_SET_SUCCESS, MainBot.class);
            } catch (Exception e) {
                log.error("Smth went wrong while setting first currency", e);
                return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_CURRENCY_SETS_CUR_SET_FAIL, MainBot.class);
            }
        } else {
            try {
                User user = userRepoService.findByUsername(username).orElseThrow(RuntimeException::new);
                CurrencySettings settings = currencySettingsRepositoryService.findByUser(user).orElseThrow(RuntimeException::new);

                Currency secondCurrency = Currency.valueOf(messageText);
                settings.setSecondCurrency(secondCurrency);

                currencySettingsRepositoryService.save(settings);
                currencyBO.setSettingMode(false);
                applicationBO.setActiveLayerController(null);

                return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_CURRENCY_SETS_SECOND_CUR_SET_SUCCESS, MainBot.class);
            } catch (Exception e) {
                log.error("Smth went wrong while setting second currency", e);
                return botAnswerService.lookForServiceResponse(BOT_ANSWER_SERVICE_CURRENCY_SETS_CUR_SET_FAIL, MainBot.class);
            }
        }
    }
}
