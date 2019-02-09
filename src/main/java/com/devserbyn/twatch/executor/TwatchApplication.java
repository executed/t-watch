package com.devserbyn.twatch.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@ComponentScan ("com.devserbyn.twatch")
public class TwatchApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TwatchApplication.class, args);
    }

}

