package com.devserbyn.twatch.model.bo;

import com.devserbyn.twatch.model.bot.BaseBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class ApplicationBO {

    private boolean developmentMode = false;
    private List<BaseBot> registeredBots = new ArrayList<>();
}
