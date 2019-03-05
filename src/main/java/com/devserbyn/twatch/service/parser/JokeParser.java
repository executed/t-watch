package com.devserbyn.twatch.service.parser;

import com.devserbyn.twatch.constant.STR_CONST;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class JokeParser implements Parser{

    @Override
    public void processTextByZPath(String host, String path) {

    }
}
