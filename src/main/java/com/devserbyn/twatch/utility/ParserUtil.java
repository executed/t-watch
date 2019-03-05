package com.devserbyn.twatch.utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParserUtil {

    public Optional<Document> convertJsoupDocToW3c(org.jsoup.nodes.Document document) {
        try {
            TagNode tagNode = new HtmlCleaner().clean(document.html());
            Document convertedDoc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            return Optional.of(convertedDoc);
        } catch (ParserConfigurationException e) {
            log.error("Error during converting Jsoup doc to W3C", e);
            return Optional.empty();
        }
    }
}
