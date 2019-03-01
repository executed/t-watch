package com.devserbyn.twatch.service.impl;

import com.devserbyn.twatch.constant.ARGS_CONST;
import com.devserbyn.twatch.model.bo.ApplicationBO;
import com.devserbyn.twatch.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationBO applicationBO;

    @Override
    public void resolveApplicationArgs(String[] args) {
        List<String> argumentList = Arrays.asList(args);

        if (argumentList.contains(ARGS_CONST.DEV_MODE)){
            applicationBO.setDevelopmentMode(true);
            log.warn("Application running in development mode");
        } else {
            log.warn("Application running in production mode");
        }
    }
}
