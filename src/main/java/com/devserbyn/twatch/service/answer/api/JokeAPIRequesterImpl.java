package com.devserbyn.twatch.service.answer.api;

import com.devserbyn.twatch.constant.PATH_CONST;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySources({
        @PropertySource(PATH_CONST.PROPERTY_API)
})
public class JokeAPIRequesterImpl implements JokeAPIRequester{

    private final Environment env;

    @Override
    @SuppressWarnings ("unchecked")
    public Optional<String> getRandomJoke() {
        // TODO: Request jokes from various API
        // Hardcoded
        try {
            String jokeAPIHost = env.getProperty("joke.yomomma.host");
            String jsonField = env.getProperty("joke.yomomma.json.field");
            Map<String, String> map = new ObjectMapper().readValue(jokeAPIHost, Map.class);
            return Optional.of(map.get(jsonField));
        } catch (IOException e) {
            log.error("Something went wrong while getting random joke from API", e);
            return Optional.empty();
        }
    }
}
