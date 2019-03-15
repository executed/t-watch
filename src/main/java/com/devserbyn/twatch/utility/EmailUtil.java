package com.devserbyn.twatch.utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
public class EmailUtil {

    @Value ("classpath:string/bot/answers/mainbot_answers.txt")
    Resource resourceFile;

    public Optional<File> getDictionaryAttachment() {
        try {
            return Optional.of(this.resourceFile.getFile());
        } catch (IOException e) {
            log.error("Something went wrong while getting dictionary file", e);
            return Optional.empty();
        }
    }
}
