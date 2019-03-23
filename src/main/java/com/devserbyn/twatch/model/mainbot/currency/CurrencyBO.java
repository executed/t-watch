package com.devserbyn.twatch.model.mainbot.currency;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CurrencyBO {

    private boolean settingMode;
    private boolean firstCurrencySet;

    public void disableSettingsMode() {
        this.settingMode = false;
        this.firstCurrencySet = false;
    }
}
