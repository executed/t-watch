package com.devserbyn.twatch.model.bo;

import com.devserbyn.twatch.controller.LayerController;
import com.devserbyn.twatch.model.bot.BaseBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.generics.BotSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Getter
@Setter
public class ApplicationBO {

    private boolean developmentMode = false;
    private Map<BaseBot, BotSession> registeredBots = new HashMap<>();
    private LayerController activeLayerController;
}
