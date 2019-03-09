package com.devserbyn.twatch.service.parser;

import com.devserbyn.twatch.utility.ParserUtil;
import com.devserbyn.twatch.utility.WebUtil;
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
    private final WebUtil webUtil;

    public Optional<String> processZPath(String url, String xPath) {
        Document document = parserUtil.convertJsoupDocToW3c(webUtil.getDocumentByURL(url))
                                      .orElseThrow(RuntimeException::new);
        String result = this.getElementValueByXPath(document, xPath);
        return Optional.of(result);
    }
}
