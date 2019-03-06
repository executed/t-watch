package com.devserbyn.twatch.service.answer.api;

import com.devserbyn.twatch.model.JokeAPIParams;
import com.devserbyn.twatch.service.parser.JokeParser;
import com.devserbyn.twatch.utility.JsonUtil;
import com.devserbyn.twatch.utility.PropertyUtil;
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
    private final JsonUtil jsonUtil;

    @Override
    public Optional<String> getRandomJoke() {
        return this.getApiJoke();
    }

    private Optional<String> getApiJoke() {
        try {
            final JokeAPIParams params = propertyUtil.getRandomJokeAPIParams();
            String json = Jsoup.connect(params.getHost())
                    .ignoreContentType(true)
                    .userAgent(params.getUserAgent())
                    .execute()
                    .body();
            String result = jsonUtil.getJsonNodeTextByPath(json, params.getJsonNodePath());
            return Optional.of(result);
        } catch (IOException e) {
            log.error("Something wrong while getting joke string from API", e);
            return Optional.empty();
        }
    }
}
