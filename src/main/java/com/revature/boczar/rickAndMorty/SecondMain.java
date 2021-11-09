package com.revature.boczar.rickAndMorty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.boczar.rickAndMorty.Model.Character;
import com.revature.boczar.rickAndMorty.Model.Filtered;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SecondMain {


    public static void main(String[] args) throws IOException {


        URL url = new URL("https://rickandmortyapi.com/api/character/?name=bird");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream inputStream = connection.getInputStream();

        ObjectMapper objectMapper = new ObjectMapper();

        String json = new String(inputStream.readAllBytes());

        JsonNode rootNode = objectMapper.readTree(json);

        //System.out.println(rootNode);

        JsonNode node = rootNode.path("info").path("count");

        String s = node.toString();

        System.out.println(s);


    }
}
