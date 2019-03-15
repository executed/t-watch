package com.devserbyn.twatch.model.bo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BotAnswerBO {

    private boolean dictionaryModified = false;
}
