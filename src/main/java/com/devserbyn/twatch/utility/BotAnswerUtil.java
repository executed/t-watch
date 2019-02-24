package com.devserbyn.twatch.utility;

import com.devserbyn.twatch.constant.STR_CONST;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

@Component
public class BotAnswerUtil {

    public static String processRandomAnswer(String fileName, String key) throws IOException {
        ArrayList<String> foundLines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                String foundKey = line.substring(0, (line.indexOf("=") - 1));
                if (foundKey.equals(key)) {
                    foundLines.add(line);
                }
            });
        }
        if (foundLines.size() == 0) {
            return STR_CONST.BOT_ANSWER_DEFAULT;
        }
        String answerFoundLine = foundLines.get(new Random().nextInt(foundLines.size()));
        return answerFoundLine.substring((answerFoundLine.indexOf("=") + 1));
    }
}
