package com.revature.boczar.rickAndMorty.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.boczar.rickAndMorty.Model.Character;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class IdRepository {

    public Map<Integer, String> getMapOfNames() {
        Map<Integer, String> mapOfNames = new HashMap<>();
        for (int i = 1; i <= 42; i++) {
            URL url = null;
            try {
                url = new URL("https://rickandmortyapi.com/api/character/?page=" + i);
            } catch (MalformedURLException e) {
                log.error(e.getMessage());
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = new String(inputStream.readAllBytes());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            JsonNode rootNode = null;
            try {
                rootNode = objectMapper.readTree(json);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
            JsonNode node = rootNode.path("results");
            String listOfCharacters = node.toString();
            List<Character> list = null;
            try {
                list = objectMapper.readValue(listOfCharacters, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
            for (Character character : list) {
                mapOfNames.put(character.getId(), character.getName().toLowerCase(Locale.ROOT));
            }
        }
        return mapOfNames;
    }

    public Map<Integer, String> searchByName(String name, Map<Integer, String> mapOfNames) {

        return mapOfNames.entrySet()
                .stream()
                .filter(map -> map.getValue().contains(name.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

//    public static void main(String[] args) {
//        Map<Integer, String> mapOfNames = getMapOfNames();
//
//      // mapOfNames.forEach((k,v)-> System.out.println(k + " " + v));
//
//        Map<Integer, String> bird = searchByName("mort", mapOfNames);
//
//        bird.forEach((k,v)-> System.out.println(k + " " + v));
//
//
//    }
}

