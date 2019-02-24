package com.devserbyn.twatch.utility;

import com.devserbyn.twatch.annotation.Profiled;
import com.devserbyn.twatch.constant.STR_CONST;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

@Component
public class BotAnswerUtil {

    public static String processRandomAnswer(String fileName, String key) throws IOException {
        ArrayList<String> foundLines = new ArrayList<>();
        File file = new ClassPathResource(fileName).getFile();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String foundKey = line.substring(0, (line.indexOf("=") - 1));
                if (foundKey.equals(key)) {
                    foundLines.add(line);
                }
            }
        }
        if (foundLines.size() == 0) {
            return STR_CONST.BOT_ANSWER_DEFAULT;
        }
        String answerFoundLine = foundLines.get(new Random().nextInt(foundLines.size()));
        return answerFoundLine.substring((answerFoundLine.indexOf("=") + 1));
    }

    public static void addNewBotAnswer(String fileName, String answerLine) throws IOException {
        /*if (!answerLine.matches(STR_CONST.BOT_ANSWER_WRITE_REGEX)) {
            throw new IllegalArgumentException();
        }*/
        File file = new ClassPathResource(fileName).getFile();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.newLine();
            writer.write(answerLine);
        }
    }
 }
