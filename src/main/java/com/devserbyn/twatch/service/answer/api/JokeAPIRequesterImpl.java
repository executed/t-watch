package com.devserbyn.twatch.service.answer.api;

import com.devserbyn.twatch.constant.PATH_CONST;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public Optional<String> getRandomJoke() {
        // TODO: Request jokes from various API
        // Hardcoded
        try {
            String jokeAPIHost = env.getProperty("joke.yomomma.host");
            String jsonField = env.getProperty("joke.yomomma.json.field");
            String json = Jsoup.connect(jokeAPIHost).ignoreContentType(true).execute().body();
            final ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
            return (node.has(jsonField)) ? Optional.of(node.get(jsonField).asText())
                                         : Optional.empty();
        } catch (IOException e) {
            log.error("Something wrong while getting joke string from API", e);
            return Optional.empty();
        }
    }
}
