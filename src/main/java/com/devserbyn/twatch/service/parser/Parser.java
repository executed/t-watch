package com.devserbyn.twatch.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public interface Parser {

    String processZPath(String host, String path);

    default Document getAndConnectDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    default Element getElementByZPath(Element element, List<String> ZPathList, int counter){
        if(counter == ZPathList.size()) {
            return element;
        }
        Element nextElement;
        String node = ZPathList.get(counter);
        if (node.contains("child")) {
            nextElement = element.child(Integer.valueOf("" + node.charAt(7)));
        }
        else nextElement = element.select(ZPathList.get(counter)).first();
        return getElementByZPath(nextElement, ZPathList, ++counter);
    }
}
