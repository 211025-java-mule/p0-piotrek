package com.revature.boczar.rickAndMorty;

import com.revature.boczar.rickAndMorty.Util.ApplicationContext;
import com.revature.boczar.rickAndMorty.Util.CharacterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class RickAndMortyAPITest {
    static URL url;
    static HttpURLConnection connection;
    static ApplicationContext applicationContext;

    @Before
    public void setUpConnection() {
        try {
            url = new URL("https://rickandmortyapi.com/api/character/");
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        applicationContext = new ApplicationContext();
    }
    @Test
    public void testConnectionToAPI() {
        Assert.assertNotEquals(null, connection);
    }
    @Test
    public void testGetBody(){
        CharacterService characterService = applicationContext.getCharacterService();
        String body = characterService.getBody(1);
        Assert.assertNotEquals(null , body);
    }
}
