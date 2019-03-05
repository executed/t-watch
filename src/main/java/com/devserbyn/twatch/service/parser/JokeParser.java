package com.devserbyn.twatch.service.parser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class JokeParser implements Parser{


    @Override
    public String processZPath(String host, String path) {
        return null;
    }
}
