package com.devserbyn.twatch.service.parser;

import com.devserbyn.twatch.utility.ParserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JokeParser implements Parser {

    private final ParserUtil parserUtil;

    public Optional<String> processZPath(String url, String zPathString) {
        Document document = parserUtil.convertJsoupDocToW3c(getAndConnectDocument(url))
                                      .orElseThrow(RuntimeException::new);
        String result = this.getElementValueByZPath(document, zPathString);
        return Optional.of(result);
    }
}
