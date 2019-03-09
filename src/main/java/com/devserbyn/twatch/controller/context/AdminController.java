package com.devserbyn.twatch.controller.context;

import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.model.bot.MainBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MainBot bot;
    private final ApplicationBO applicationBO;

    @GetMapping("/start")
    public ModelAndView addEvent() throws TelegramApiRequestException {

        if (!applicationBO.getRegisteredBots().contains(bot)) {
            new TelegramBotsApi().registerBot(bot);
            applicationBO.getRegisteredBots().add(this.bot);
            log.info("Bot {} was registered", MainBot.class);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @GetMapping("/error")
    public String errorPage()  {
        return "error";
    }
}
