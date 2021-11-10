package com.revature.boczar.rickAndMorty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.boczar.rickAndMorty.Model.Character;
import com.revature.boczar.rickAndMorty.Util.CharacterPostgresRepository;
import com.revature.boczar.rickAndMorty.Util.CharacterService;
import com.revature.boczar.rickAndMorty.Util.IdRepository;
import com.revature.boczar.rickAndMorty.Util.UserInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

@Slf4j
public class RickAndMorty {


    public static void main(String[] args) {
        CharacterService characterService = new CharacterService();
        UserInterface userInterface = new UserInterface();
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterPostgresRepository characterPostgresRepository = new CharacterPostgresRepository();
        IdRepository idRepository = new IdRepository();

        while (true) {
            System.out.println("""
                    Welcome to Rick & Morty App! Please pick number from 1 to 826 to retrieve information about Rick & Morty character or we can pick for You!
                     1 - Enter your number
                     2 - Search for character by name
                     3 - Select random character
                     0 - Exit application""");
            Scanner scanner = new Scanner(System.in);
            if (!(scanner.hasNextInt())) {
                System.out.println("Please input an integer");
            } else {
                int i = scanner.nextInt();

                switch (i) {
                    case 1 -> {
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
                    case 2 -> {
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
                                System.out.println("These are "+resultsSize+ " result(s) of your search:");
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
                    case 3 -> {
                        Random random = new Random();
                        IntStream ints = random.ints(1, 826);
                        int randomInt = ints.findFirst().getAsInt();
                        String body = characterService.getBody(randomInt);
                        Character character = userInterface.getCharacterFromJson(objectMapper, body);
                        characterPostgresRepository.saveCharacterToDB(character);
                        characterPostgresRepository.printAllCharacters();
                    }
                    case 0 -> {
                        System.out.println("Bye bye");
                        System.exit(0);
                    }
                }
            }
        }
    }
}


