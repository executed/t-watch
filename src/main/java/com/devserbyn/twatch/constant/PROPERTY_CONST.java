package com.devserbyn.twatch.constant;

public final class PROPERTY_CONST {

    public static final String BOT_TOKEN_KEY_FORMAT = "bot.%s.token";
    public static final String BOT_USERNAME_KEY_FORMAT = "bot.%s.username";

    public static final String JMX_ANNOTATION_DOMAIN = "jmx.annotation.domain";
    public static final String JMX_ANNOTATION_KEY = "jmx.annotation.%s.key";
    public static final String JMX_ANNOTATION_VALUE = "jmx.annotation.%s.value";

    public static final String DEPLOYMENT_STARTUP_LOAD_TIMEOUT = "deployment.startup.pageLoadTimeout";
    public static final String DEPLOYMENT_PAGE_LOAD_TIMEOUT = "deployment.pages.pageLoadTimeout";
    public static final String DEPLOYMENT_STARTUP_LOAD_TIMEOUT_EL = "${deployment.startup.pageLoadTimeout}";
    public static final String DEPLOYMENT_PAGE_LOAD_TIMEOUT_EL = "${deployment.pages.pageLoadTimeout}";

    public static final String API_JOKE_SERV_NAMES = "api.joke.supportedServices";
    public static final String PARSER_JOKE_SERV_NAMES = "parser.joke.supportedServices";
    public static final String API_JOKE_SERV_NAMES_SPLITERATOR = ", ";
    public static final String API_JOKE_JSON_PATH_SPLITERATOR = ", ";
    public static final String PARSER_JOKE_SERV_NAMES_SPLITERATOR = ", ";
    public static final String API_JOKE_HOST_FORMAT = "api.joke.%s.host";
    public static final String PARSER_JOKE_HOST_FORMAT = "parser.joke.%s.host";
    public static final String API_JOKE_JSON_FIELD_FORMAT = "api.joke.%s.json.field";
    public static final String API_JOKE_BASIC_USER_AGENT = "api.userAgent.basic";
    public static final String PARSER_JOKE_XPATH_FORMAT = "parser.joke.%s.xpath";
    public static final String API_JSON_EMPTY_NODE = "e_node";

}
