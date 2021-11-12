package com.revature.boczar.rickAndMorty;

import com.revature.boczar.rickAndMorty.Model.Character;
import com.revature.boczar.rickAndMorty.Model.RmObject;
import com.revature.boczar.rickAndMorty.Util.ApplicationContext;
import com.revature.boczar.rickAndMorty.Util.CharacterService;
import com.revature.boczar.rickAndMorty.Util.IdRepository;
import com.revature.boczar.rickAndMorty.Util.UserInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CharacterUtilTest {
    public static ApplicationContext applicationContext;

    @Before
    public void setup(){
        applicationContext = new ApplicationContext();
    }

    @Test
    public void testGetMapOfNames(){
        IdRepository idRepository = applicationContext.getIdRepository();
        Map<Integer, String> mapOfNames = idRepository.getMapOfNames();
        Assert.assertEquals(826, mapOfNames.size() );
    }

    @Test
    public void testSearchByName(){
        IdRepository idRepository = applicationContext.getIdRepository();
        Map<Integer, String> mapOfNames = idRepository.getMapOfNames();
        Map<Integer, String> mapOfBird = idRepository.searchByName("bird", mapOfNames);
        Assert.assertEquals(4, mapOfBird.size() );
    }

    @Test
    public void testGetCharFromJson() throws MalformedURLException {
        UserInterface userInterface = applicationContext.getUserInterface();
        CharacterService characterService = applicationContext.getCharacterService();
        String body = characterService.getBody(90);
        Character characterFromJson = userInterface.getCharacterFromJson(applicationContext.getObjectMapper(), body);

        RmObject origin = new RmObject();
        origin.setName("Unity's Planet");
        origin.setUrl("https://rickandmortyapi.com/api/location/28");

        RmObject location = new RmObject();
        location.setName("Unity's Planet");
        location.setUrl("https://rickandmortyapi.com/api/location/28");
        List<URL> urlList = new ArrayList<>();
        urlList.add(new URL("https://rickandmortyapi.com/api/episode/14"));

        Character daronJefferson = new Character();
        daronJefferson.setId(90);
        daronJefferson.setName("Daron Jefferson");
        daronJefferson.setStatus("Alive");
        daronJefferson.setSpecies("Alien");
        daronJefferson.setType("Cone-nippled alien");
        daronJefferson.setGender("Male");
        daronJefferson.setOrigin(origin);
        daronJefferson.setLocation(location);
        daronJefferson.setImage("https://rickandmortyapi.com/api/character/avatar/90.jpeg");
        daronJefferson.setEpisode(urlList);
        daronJefferson.setUrl("https://rickandmortyapi.com/api/character/90");
        daronJefferson.setCreated("2017-12-01T10:54:34.736Z");

        Assert.assertEquals(daronJefferson , characterFromJson);
    }

}
