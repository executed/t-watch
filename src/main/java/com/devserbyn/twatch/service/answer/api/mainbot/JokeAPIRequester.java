package com.devserbyn.twatch.service.answer.api.mainbot;

import java.util.Optional;

public interface JokeAPIRequester {

    Optional<String> getRandomJoke();
}
