package com.devserbyn.twatch;

import com.devserbyn.twatch.executor.TwatchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig
@WebAppConfiguration
public class TwatchApplicationTests {

    @Autowired
    ApplicationContext context;

    @Test
    public void contextLoads() {
    }

}

