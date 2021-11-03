package Util;

import Model.Character;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UI {

    public Character getCharacter(ObjectMapper objectMapper, String body) {
        Character character = null;
        try {
            character = objectMapper.readValue(body, Character.class);
        } catch (JsonProcessingException e) {
            log.error("Could not parse response");
        }
        return character;
    }
}
