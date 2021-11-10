package com.revature.boczar.rickAndMorty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.boczar.rickAndMorty.Util.CharacterPostgresRepository;
import com.revature.boczar.rickAndMorty.Util.CharacterService;
import com.revature.boczar.rickAndMorty.Util.IdRepository;
import com.revature.boczar.rickAndMorty.Util.UserInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

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
                    Welcome to Rick & Morty App where you can find all information about favorite character! Please select your option:
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


