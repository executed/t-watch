package com.devserbyn.twatch.service.answer.api;

import java.util.Optional;

public interface JokeAPIRequester {

    Optional<String> getRandomJoke();
}
