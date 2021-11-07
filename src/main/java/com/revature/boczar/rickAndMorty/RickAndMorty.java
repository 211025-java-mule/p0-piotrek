package com.revature.boczar.rickAndMorty;

import com.revature.boczar.rickAndMorty.Model.Character;
import com.revature.boczar.rickAndMorty.Util.CharacterService;
import com.revature.boczar.rickAndMorty.Util.CharacterPostgresRepository;
import com.revature.boczar.rickAndMorty.Util.UserInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class RickAndMorty {
    public static void main(String[] args) {
        CharacterService characterService = new CharacterService();
        UserInterface userInterface = new UserInterface();
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterPostgresRepository characterPostgresRepository = new CharacterPostgresRepository();

        while (true) {
            System.out.println("Welcome to Rick & Morty App! Please pick number from 1 to 826 to retrieve information about Rick & Morty character! ");
            Scanner scanner = new Scanner(System.in);
            if (!(scanner.hasNextInt())) {
                System.out.println("Please input an integer");
            } else {

                int usersChoice = scanner.nextInt();

                if (usersChoice == 0) {
                    System.out.println("Bye bye!");
                    System.exit(0);
                }
                String body = characterService.getBody(usersChoice);
                Character character = userInterface.getCharacter(objectMapper, body);

                //DB
                characterPostgresRepository.saveCharacterToDB(character);
                characterPostgresRepository.printAllCharacters();
            }
        }
    }
}


