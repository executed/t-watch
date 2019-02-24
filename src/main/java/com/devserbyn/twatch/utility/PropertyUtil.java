package com.devserbyn.twatch.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.Objects;

import static com.devserbyn.twatch.constant.PROPERTY_CONST.*;

@Component
@RequiredArgsConstructor
@PropertySources(
    {
      @PropertySource ("classpath:bot.properties"),
      @PropertySource ("classpath:jmx.properties")
    }
)
public class PropertyUtil {

    private final Environment environment;

    public String getBotPropertyByFormat(String className, String format) {
        String propertyKey = String.format(format, className);
        String value = environment.getProperty(String.format(propertyKey, className));
        return Objects.requireNonNull(value, "No property with such class name: " + className);
    }

    public ObjectName getJmxObjectName(Class annotationClass) throws MalformedObjectNameException {
        String annotationName = annotationClass.getSimpleName();
        String domain = environment.getProperty(JMX_ANNOTATION_DOMAIN);
        String key = environment.getProperty(String.format(JMX_ANNOTATION_KEY, annotationName));
        String value = environment.getProperty(String.format(JMX_ANNOTATION_VALUE, annotationName));

        return new ObjectName(domain, key, value);
    }
}
