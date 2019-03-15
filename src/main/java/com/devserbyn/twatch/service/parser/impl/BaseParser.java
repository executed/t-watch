package com.devserbyn.twatch.service.parser.impl;

import com.devserbyn.twatch.service.parser.Parser;
import com.devserbyn.twatch.utility.ParserUtil;
import com.devserbyn.twatch.utility.WebUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

@Service
@AllArgsConstructor
@Slf4j
public class BaseParser implements Parser {

    private final ParserUtil parserUtil;
    private final WebUtil webUtil;

    private String processXPath(Document document, String xPath) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            return (String) xpath.evaluate(xPath, document, XPathConstants.STRING);
        } catch (Exception e) {
            throw new RuntimeException("Error during processing XPath", e);
        }
    }

    public String processXPath(String url, String xPath) {
        Document document = parserUtil.convertJsoupDocToW3c(webUtil.getDocumentByURL(url))
                            .orElseThrow(RuntimeException::new);
        return this.processXPath(document, xPath);
    }
}
