package com.revature.boczar.rickAndMorty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.boczar.rickAndMorty.Util.*;

import java.util.Scanner;


public class RickAndMorty {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        CharacterService characterService = applicationContext.getCharacterService();
        UserInterface userInterface = applicationContext.getUserInterface();
        ObjectMapper objectMapper = applicationContext.getObjectMapper();
        CharacterPostgresRepository characterPostgresRepository = applicationContext.getCharacterPostgresRepository();
        IdRepository idRepository = applicationContext.getIdRepository();

        while (true) {
            System.out.println("""
                    Welcome to Rick & Morty App where you can find all information about your favorite character! Please select your option:
                     1 - Search by ID
                     2 - Search by name
                     3 - Select random character
                     0 - Exit application
                     """);
            Scanner scanner = new Scanner(System.in);
            if (!(scanner.hasNextInt())) {
                System.out.println("Please input an integer");
            } else {
                int i = scanner.nextInt();

                switch (i) {
                    case 1 -> userInterface.searchById(scanner, characterService, userInterface, objectMapper, characterPostgresRepository);
                    case 2 -> userInterface.searchByName(idRepository, characterService, userInterface, characterPostgresRepository, objectMapper);
                    case 3 -> userInterface.getRandomCharacter(characterService, userInterface, objectMapper, characterPostgresRepository);
                    case 0 -> userInterface.exitApplication();
                }
            }
        }
    }
}


