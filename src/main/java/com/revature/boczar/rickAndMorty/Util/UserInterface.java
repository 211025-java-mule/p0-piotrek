package com.revature.boczar.rickAndMorty.Util;

import com.revature.boczar.rickAndMorty.Model.Character;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInterface {
    /**
     * Method converts Json from String to Character object.
     * @param objectMapper Method accepts objectMapper to perform reading.
     * @param body Method also accept body, which is String in Json format.
     * @return Object of Character class is returned.
     */
    public Character getCharacterFromJson(ObjectMapper objectMapper, String body) {
        Character character = null;
        try {
            character = objectMapper.readValue(body, Character.class);
        } catch (JsonProcessingException e) {
            log.error("Could not parse response");
        }
        return character;
    }
}
