package com.devserbyn.twatch.constant;

public final class PROPERTY_CONST {

    public static final String BOT_TOKEN_KEY_FORMAT = "bot.%s.token";
    public static final String BOT_USERNAME_KEY_FORMAT = "bot.%s.username";

    public static final String JMX_ANNOTATION_DOMAIN = "jmx.annotation.domain";
    public static final String JMX_ANNOTATION_KEY = "jmx.annotation.%s.key";
    public static final String JMX_ANNOTATION_VALUE = "jmx.annotation.%s.value";

    public static final String DEPLOYMENT_PREV_SCHED_CRON_EXP = "${deployment.preventScheduling.cronExp}";
    public static final String DEPLOYMENT_ACTIV_STAT_MSG_CRON_EXP = "${deployment.activityStatusMessage.cronExp}";
    public static final String DEPLOYMENT_SEND_DICT_FILE_CRON_EXP = "${deployment.sendDictionaryFile.cronExp}";
    public static final String DEPLOYMENT_CONTEXT_PATH = "deployment.contextPath";
    public static final String DEPLOYMENT_PREV_SCHED_LOAD_TIMEOUT = "deployment.preventScheduling.pageLoadTimeout";

    public static final String API_JOKE_SERV_NAMES = "api.joke.supportedServices";
    public static final String API_JOKE_SERV_NAMES_SPLITERATOR = ", ";
    public static final String API_JOKE_JSON_PATH_SPLITERATOR = ", ";
    public static final String API_JOKE_HOST_FORMAT = "api.joke.%s.host";
    public static final String API_JOKE_JSON_FIELD_FORMAT = "api.joke.%s.json.field";

}
