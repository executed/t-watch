package com.devserbyn.twatch.utility;

import com.devserbyn.twatch.constant.STR_CONST;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
// TODO: Make a job that valid containment of dictionary file
@Component
public class BotAnswerUtil {

    public static String processRandomAnswer(String fileName, String key) throws IOException {
        ArrayList<String> foundLines = new ArrayList<>();
        File file = new ClassPathResource(fileName).getFile();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            boolean occurrenceFound = false;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("=")) {
                    continue;
                }
                String foundKey = line.substring(0, (line.indexOf("=") - 1));
                if (foundKey.equals(key)) {
                    occurrenceFound = true;
                    foundLines.add(line);
                } else if (occurrenceFound) {
                    // As file is already sorted - lines are sorted too
                    // Then we have no need to look for searched keys further
                    break;
                }
            }
        }
        if (foundLines.size() == 0) {
            return STR_CONST.BOT_ANSWER_DEFAULT;
        }
        String answerFoundLine = foundLines.get(new Random().nextInt(foundLines.size()));
        return answerFoundLine.substring((answerFoundLine.indexOf("=") + 1));
    }

    // TODO: Answer must be added between two lines in sorted sequence - not append
    public static void addNewBotAnswer(String fileName, String answerLine) throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.newLine();
            writer.write(answerLine);
        }
    }

    public static BotApiMethod wrapIntoApiMethod(String text, Update update) {
        return wrapIntoApiMethod(text, update, false);
    }

    public static BotApiMethod wrapIntoApiMethod(String text, Update update, boolean md) {
        text = replaceNewLineChars(text);
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId(), text);
        sendMessage.enableMarkdown(md);
        return sendMessage;
    }

    public static String replaceNewLineChars(String text) {
        return text.replaceAll(STR_CONST.BOT_ANSWER_NEW_LINE_CHAR, System.lineSeparator());
    }
 }
