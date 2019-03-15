package com.devserbyn.twatch.utility;

import org.jsoup.nodes.Document;

public interface WebUtil {

    void accessURL(String url, int timeout);

    void accessURL(String url);

    Document getDocumentByURL(String url);

    Document getDocumentByURL(String url, int timeout);

    String getJsonByURL(String url);

    String getJsonByURL(String url, int timeout);
}
