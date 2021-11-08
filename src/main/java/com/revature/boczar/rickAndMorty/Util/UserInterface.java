package com.revature.boczar.rickAndMorty.Util;

import com.revature.boczar.rickAndMorty.Model.Character;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInterface {

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
