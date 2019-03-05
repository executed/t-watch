package com.devserbyn.twatch.utility;

import com.devserbyn.twatch.constant.PATH_CONST;
import com.devserbyn.twatch.model.JokeAPIParams;
import com.devserbyn.twatch.model.JokeParsedParams;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.Objects;
import java.util.Random;

import static com.devserbyn.twatch.constant.PROPERTY_CONST.*;

@Component
@RequiredArgsConstructor
@PropertySources(
    {
      @PropertySource (PATH_CONST.PROPERTY_BOT), @PropertySource (PATH_CONST.PROPERTY_JMX),
      @PropertySource (PATH_CONST.PROPERTY_DEPLOYMENT), @PropertySource(PATH_CONST.PROPERTY_API)
    }
)
public class PropertyUtil {

    private final Environment env;

    public String getBotPropertyByFormat(String className, String format) {
        String propertyKey = String.format(format, className);
        String value = env.getProperty(String.format(propertyKey, className));
        return Objects.requireNonNull(value, "No property with such class name: " + className);
    }

    public ObjectName getJmxObjectName(Class annotationClass) throws MalformedObjectNameException {
        String annotationName = annotationClass.getSimpleName();
        String domain = env.getProperty(JMX_ANNOTATION_DOMAIN);
        String key = env.getProperty(String.format(JMX_ANNOTATION_KEY, annotationName));
        String value = env.getProperty(String.format(JMX_ANNOTATION_VALUE, annotationName));

        return new ObjectName(domain, key, value);
    }

    public JokeAPIParams getRandomJokeAPIParams() {
        String[] supportedApiNames = env.getProperty(API_JOKE_SERV_NAMES)
                                        .split(API_JOKE_SERV_NAMES_SPLITERATOR);
        int randomApiNameIdx = new Random().nextInt(supportedApiNames.length);
        String hostProperty = String.format(API_JOKE_HOST_FORMAT, supportedApiNames[randomApiNameIdx]);
        String jsonFieldProperty = String.format(API_JOKE_JSON_FIELD_FORMAT, supportedApiNames[randomApiNameIdx]);

        return JokeAPIParams.builder().host(env.getProperty(hostProperty))
                                      .jsonNodePath(env.getProperty(jsonFieldProperty))
                                      .build();
    }

    public JokeParsedParams getRandomJokeParsedParams() {
        String[] supportedApiNames = env.getProperty(PARSER_JOKE_SERV_NAMES)
                                        .split(PARSER_JOKE_SERV_NAMES_SPLITERATOR);
        int randomApiNameIdx = new Random().nextInt(supportedApiNames.length);
        String hostProperty = String.format(PARSER_JOKE_HOST_FORMAT, supportedApiNames[randomApiNameIdx]);
        String xpathProperty = String.format(PARSER_JOKE_XPATH_FORMAT, supportedApiNames[randomApiNameIdx]);

        return JokeParsedParams.builder().host(env.getProperty(hostProperty))
                                         .xPath(env.getProperty(xpathProperty))
                                         .build();
    }
}
