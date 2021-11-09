package com.revature.boczar.rickAndMorty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.boczar.rickAndMorty.Model.Character;
import com.revature.boczar.rickAndMorty.Util.CharacterPostgresRepository;
import com.revature.boczar.rickAndMorty.Util.CharacterService;
import com.revature.boczar.rickAndMorty.Util.UserInterface;
import lombok.extern.slf4j.Slf4j;

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

        while (true) {
            System.out.println("""
                    Welcome to Rick & Morty App! Please pick number from 1 to 826 to retrieve information about Rick & Morty character or we can pick for You!
                     1 - Enter your number
                     2 - Select random character
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


