package com.devserbyn.twatch.service.answer.api;

import com.devserbyn.twatch.model.JokeAPIParams;
import com.devserbyn.twatch.utility.PropertyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JokeAPIRequesterImpl implements JokeAPIRequester{

    private final PropertyUtil propertyUtil;

    @Override
    public Optional<String> getRandomJoke() {
        // TODO: Request jokes from various API
        try {
            final JokeAPIParams params = propertyUtil.getRandomJokeAPIParams();
            String json = Jsoup.connect(params.getHost())
                               .ignoreContentType(true)
                               .execute()
                               .body();
            final ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
            // If there are no fields in json (joke string based in root node)
            if (params.getJsonFieldName() == null) {
                return Optional.of(node.asText());
            }
            if (node.has(params.getJsonFieldName())) {
                return Optional.of(node.get(params.getJsonFieldName()).asText());
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            log.error("Something wrong while getting joke string from API", e);
            return Optional.empty();
        }
    }
}
