package com.revature.boczar.rickAndMorty.Util;

import com.revature.boczar.rickAndMorty.Model.Character;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    /**
     * Method searches for character by ID given by User. Then this character is shown in console and added to database
     * @param scanner Object of Scanner. It is choice of option made by user
     * @param characterService Object of CharacterService in order to be able to run getBody();
     * @param userInterface Object of UserInterface in order to be able to run getCharacterFromJson();
     * @param objectMapper Object of ObjectMapper in order to be able to run getCharacterFromJson();
     * @param characterPostgresRepository Object of CharacterPostgresRepository in order to be able to run saveCharacterToDB(); and printAllCharacters();
     */

    public void searchById(Scanner scanner , CharacterService characterService, UserInterface userInterface, ObjectMapper objectMapper, CharacterPostgresRepository characterPostgresRepository){
        System.out.println("Please enter number from 1 to 826");
        if (!(scanner.hasNextInt())) {
            System.out.println("Please input an integer");
        } else {
            int usersChoice = scanner.nextInt();

            String body = characterService.getBody(usersChoice);
            Character character = userInterface.getCharacterFromJson(objectMapper, body);

            characterPostgresRepository.saveCharacterToDB(character);
            characterPostgresRepository.printAllCharacters();
        }
    }

    /**
     * Method searches in mapOfNames for name that is given by User. Then it shows all of ids and names that matched his/her
     * searching. Next method ask to specify id of character that user meant. Character is finally added to databased and shown in console
     * @param idRepository Object of IdRepository in order to be able to run getMapOfNames();
     * @param characterService Object of CharacterService in order to be able to run getBody();
     * @param userInterface Object of UserInterface in order to be able to run getCharacterFromJson();
     * @param characterPostgresRepository Object of CharacterPostgresRepository in order to be able to run saveCharacterToDB(); and printAllCharacters();
     * @param objectMapper Object of ObjectMapper in order to be able to run getCharacterFromJson();
     */

    public void searchByName(IdRepository idRepository, CharacterService characterService, UserInterface userInterface , CharacterPostgresRepository characterPostgresRepository, ObjectMapper objectMapper){
        System.out.println("Please enter name of a character");
        Scanner nameScanner = new Scanner(System.in);
        if (!(nameScanner.hasNext())) {
            System.out.println("Please input a string");
        } else {
            Map<Integer, String> mapOfNames = idRepository.getMapOfNames();
            String nameForSearch = nameScanner.next();
            Map<Integer, String> results = idRepository.searchByName(nameForSearch, mapOfNames);
            int resultsSize = results.size();
            if (results.isEmpty()) {
                System.out.println("There is no match for this name");
            } else {
                System.out.println("These are " + resultsSize + " result(s) of your search:");
                results.forEach((k, v) -> System.out.println("id: " + k + " name: " + v));
                System.out.println();
                System.out.println("Please enter id of the one that you have chosen");
                Scanner scannerForChosenName = new Scanner(System.in);
                if (!(scannerForChosenName.hasNextInt())) {
                    System.out.println("Please input an integer");
                } else {
                    int usersChoice = scannerForChosenName.nextInt();

                    String body = characterService.getBody(usersChoice);
                    Character character = userInterface.getCharacterFromJson(objectMapper, body);

                    characterPostgresRepository.saveCharacterToDB(character);
                    characterPostgresRepository.printAllCharacters();
                }
            }
        }
    }

    /**
     * Method generate random number from 1 to 826, then by this number search in API to retrieve information about character. Then it adds this data to database
     * @param characterService Object of CharacterService in order to be able to run getBody();
     * @param userInterface Object of UserInterface in order to be able to run getCharacterFromJson();
     * @param objectMapper Object of ObjectMapper in order to be able to run getCharacterFromJson();
     * @param characterPostgresRepository Object of CharacterPostgresRepository in order to be able to run saveCharacterToDB(); and printAllCharacters();
     */
    public void getRandomCharacter(CharacterService characterService , UserInterface userInterface, ObjectMapper objectMapper , CharacterPostgresRepository characterPostgresRepository){
        Random random = new Random();
        IntStream ints = random.ints(1, 826);
        int randomInt = ints.findFirst().getAsInt();
        String body = characterService.getBody(randomInt);
        Character character = userInterface.getCharacterFromJson(objectMapper, body);
        characterPostgresRepository.saveCharacterToDB(character);
        characterPostgresRepository.printAllCharacters();
    }

    /**
     * Method which ends application
     */
    public void exitApplication(){
        System.out.println("Bye bye");
        System.exit(0);
    }
}
