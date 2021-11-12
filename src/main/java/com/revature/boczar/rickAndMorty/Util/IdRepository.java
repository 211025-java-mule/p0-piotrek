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
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class IdRepository {

    /**
     * Method retrieves data from global API and converts it to HashMap where key is character id and value is his/her name
     *
     * @return HashMap of id and its names
     */
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
                System.err.println(e.getMessage());
                log.error(e.getMessage());
            }
            InputStream inputStream = null;
            try {
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                log.error(e.getMessage());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String json = null;
            try {
                json = new String(inputStream.readAllBytes());
            } catch (IOException e) {
                System.err.println(e.getMessage());
                log.error(e.getMessage());
            }
            JsonNode rootNode = null;
            try {
                rootNode = objectMapper.readTree(json);
            } catch (JsonProcessingException e) {
                System.err.println(e.getMessage());
                log.error(e.getMessage());
            }
            JsonNode node = rootNode.path("results");
            String listOfCharacters = node.toString();
            List<Character> list = null;
            try {
                list = objectMapper.readValue(listOfCharacters, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                System.err.println(e.getMessage());
                log.error(e.getMessage());
            }
            for (Character character : list) {
                mapOfNames.put(character.getId(), character.getName().toLowerCase());
            }
        }
        return mapOfNames;
    }

    /**
     * Method searches in MapOfNames by name given from user;
     *
     * @param name       Parameter given by User; name that method will be searching for
     * @param mapOfNames MapOfNames - map with all ids and names matched to them
     * @return MapOfName is returned, but it is now filtered by name
     */
    public Map<Integer, String> searchByName(String name, Map<Integer, String> mapOfNames) {

        return mapOfNames.entrySet()
                .stream()
                .filter(map -> map.getValue().contains(name.toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

