package com.devserbyn.twatch.service.parser;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.Optional;

public interface Parser {

    Optional<String> processZPath(String host, String zPath);

    default String getElementValueByXPath(org.w3c.dom.Document doc, String xPath) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            return (String) xpath.evaluate(xPath, doc, XPathConstants.STRING);
        } catch (Exception e) {
            throw new RuntimeException("Error during processing XPath", e);
        }
    }
}
