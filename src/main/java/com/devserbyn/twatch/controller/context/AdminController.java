package com.devserbyn.twatch.controller.context;

import com.devserbyn.twatch.model.bot.MainBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MainBot bot;

    @GetMapping("/start")
    public ModelAndView addEvent() throws TelegramApiRequestException {
        new TelegramBotsApi().registerBot(bot);
        log.info("Bot {} was registered", MainBot.class);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
