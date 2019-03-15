package com.devserbyn.twatch.utility.impl;

import com.devserbyn.twatch.constant.PATH_CONST;
import com.devserbyn.twatch.constant.PROPERTY_CONST;
import com.devserbyn.twatch.model.WebContentResponse;
import com.devserbyn.twatch.utility.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
@PropertySources ({
        @PropertySource (PATH_CONST.PROPERTY_DEPLOYMENT), @PropertySource(PATH_CONST.PROPERTY_API)
})
public class WebUtilImpl implements WebUtil {

    @Value(PROPERTY_CONST.DEPLOYMENT_PAGE_LOAD_TIMEOUT_EL)
    private int defaultPageLoadTimeout;
    @Value(PROPERTY_CONST.API_JOKE_BASIC_USER_AGENT)
    private String defaultUserAgent;

    private static Optional<Object> execute(Connection co, WebContentResponse respType) {
        try {
            switch (respType)  {
                case VOID: {
                    co.get();
                }
                case PAGE: {
                    return Optional.of(co.get());
                }
                case JSON: {
                    return Optional.of(co.execute().body());
                }
                default: return Optional.empty();
            }
        } catch (IOException e) {
            log.error("Something wrong while accessing URL", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void accessURL(String url, int timeout) {
        Connection connection = Jsoup.connect(url)
                              .timeout(timeout)
                              .ignoreContentType(true);
        execute(connection, WebContentResponse.VOID);
    }

    @Override
    public void accessURL(String url) {
        this.accessURL(url, this.defaultPageLoadTimeout);
    }

    @Override
    public Document getDocumentByURL(String url, int timeout) {
        Connection connection = Jsoup.connect(url)
                .timeout(timeout)
                .userAgent(defaultUserAgent);
        Object document = execute(connection, WebContentResponse.PAGE).orElseThrow(RuntimeException::new);

        return (Document) document;
    }

    @Override
    public Document getDocumentByURL(String url) {
        return this.getDocumentByURL(url, defaultPageLoadTimeout);
    }

    @Override
    public String getJsonByURL(String url, int timeout) {
        Connection connection = Jsoup.connect(url)
                                     .ignoreContentType(true)
                                     .timeout(timeout)
                                     .userAgent(defaultUserAgent);
        Object json = execute(connection, WebContentResponse.JSON).orElseThrow(RuntimeException::new);

        return (String) json;
    }

    @Override
    public String getJsonByURL(String url) {
        return this.getJsonByURL(url, defaultPageLoadTimeout);
    }
}
