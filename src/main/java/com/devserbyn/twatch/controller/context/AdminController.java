package com.devserbyn.twatch.controller.context;

import com.devserbyn.twatch.constant.VIEW_CONST;
import com.devserbyn.twatch.model.bot.MainBot;
import com.devserbyn.twatch.service.BotRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final BotRegisterService botRegisterService;

    @GetMapping("/start")
    public ModelAndView registerBot() throws TelegramApiRequestException {
       botRegisterService.register(MainBot.class);

       return new ModelAndView(VIEW_CONST.SUCCESS_VIEW);
    }

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorPage()  {
        return new ModelAndView(VIEW_CONST.ERROR_VIEW);
    }
}
