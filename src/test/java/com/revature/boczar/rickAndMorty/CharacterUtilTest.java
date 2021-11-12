package com.revature.boczar.rickAndMorty;

import com.revature.boczar.rickAndMorty.Util.ApplicationContext;
import com.revature.boczar.rickAndMorty.Util.IdRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

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
}
