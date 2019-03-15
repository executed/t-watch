package com.devserbyn.twatch.service.answer.api.mainbot;

import com.devserbyn.twatch.model.JokeAPIParams;
import com.devserbyn.twatch.utility.JsonUtil;
import com.devserbyn.twatch.utility.PropertyUtil;
import com.devserbyn.twatch.utility.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JokeAPIRequesterImpl implements JokeAPIRequester{

    private final PropertyUtil propertyUtil;
    private final JsonUtil jsonUtil;
    private final WebUtil webUtil;

    @Override
    public Optional<String> getRandomJoke() {
        return this.getApiJoke();
    }

    private Optional<String> getApiJoke() {
        final JokeAPIParams params = propertyUtil.getRandomJokeAPIParams();
        String json = webUtil.getJsonByURL(params.getHost());
        String result = jsonUtil.getJsonNodeTextByPath(json, params.getJsonNodePath());

        return Optional.of(result);
    }
}
