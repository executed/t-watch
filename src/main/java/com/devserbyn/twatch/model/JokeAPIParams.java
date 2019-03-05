package com.devserbyn.twatch.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JokeAPIParams {

    private String host;
    private String jsonFieldName;
}
