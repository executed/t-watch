package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.constant.PROPERTY_CONST;
import com.devserbyn.twatch.service.BotRegisterService;
import com.devserbyn.twatch.utility.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotRegisterServiceImpl implements BotRegisterService {

    private final PropertyUtil propertyUtil;

    @Override
    public String getToken(String className) {
        return propertyUtil.getBotPropertyByFormat(className, PROPERTY_CONST.BOT_TOKEN_KEY_FORMAT);
    }

    @Override
    public String getUsername(String className) {
        return propertyUtil.getBotPropertyByFormat(className, PROPERTY_CONST.BOT_USERNAME_KEY_FORMAT);
    }
}
