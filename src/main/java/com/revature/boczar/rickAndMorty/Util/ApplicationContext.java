package com.revature.boczar.rickAndMorty.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationContext {
    private CharacterService characterService;
    private UserInterface userInterface;
    private ObjectMapper objectMapper;
    private IdRepository idRepository;
    private CharacterPostgresRepository characterPostgresRepository;

    public ApplicationContext() {
        this.characterService = new CharacterService();
        this.userInterface = new UserInterface();
        this.objectMapper = new ObjectMapper();
        this.idRepository = new IdRepository();
        this.characterPostgresRepository = new CharacterPostgresRepository();
    }

    public CharacterService getCharacterService() {
        return characterService;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public IdRepository getIdRepository() {
        return idRepository;
    }

    public CharacterPostgresRepository getCharacterPostgresRepository() {
        return characterPostgresRepository;
    }
}
