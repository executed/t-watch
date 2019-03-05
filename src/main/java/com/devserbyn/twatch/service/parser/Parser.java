package com.devserbyn.twatch.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.Optional;

public interface Parser {

    Optional<String> processZPath(String host, String zPath);

    default Document getAndConnectDocument(String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Error during getting document by URL", e);
        }
    }

    default String getElementValueByZPath(org.w3c.dom.Document doc, String xPath){
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            return (String) xpath.evaluate(xPath, doc, XPathConstants.STRING);
        } catch (Exception e) {
            throw new RuntimeException("Error during processing XPath", e);
        }
    }
}
