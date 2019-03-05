package com.devserbyn.twatch.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.devserbyn.twatch.constant.PROPERTY_CONST.API_JOKE_JSON_PATH_SPLITERATOR;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonUtil {

    public String getJsonNodeTextByPath(String json, String jsonPath) {
        try {
            List<String> nodeNames = Arrays.asList(jsonPath.split(API_JOKE_JSON_PATH_SPLITERATOR));
            final ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
            return this.getLastNodeText(node, nodeNames, 0);
        } catch (Exception e) {
            log.error("Something wrong while getting json node", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getLastNodeText(JsonNode prevNode, List<String> nodeNames, int count) {
        if (count == nodeNames.size()) {
            return prevNode.asText();
        }
        if (prevNode.has(nodeNames.get(count))) {
            return getLastNodeText(prevNode.get(nodeNames.get(count)), nodeNames, ++count);
        } else {
            throw new RuntimeException();
        }
    }
}
