package com.devserbyn.twatch.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@PropertySources(
    @PropertySource ("classpath:bot.properties")
)
public class PropertyUtil {

    private final Environment environment;

    public String getBotPropertyByFormat(String className, String format) {
        String propertyKey = String.format(format, className);
        String value = environment.getProperty(String.format(propertyKey, className));
        return Objects.requireNonNull(value, "No property with such class name: " + className);
    }
}
