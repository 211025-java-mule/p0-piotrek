package com.revature.boczar.rickAndMorty.Util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
@Slf4j
public class CharacterService {
    /**
     * This method is getting Json from external URL to String object.
     * @param choice Method accept int choice and by this id search in API.
     * @return Method returns String in Json format.
     */
    public String getBody(int choice) {
        URL url = null;
        try {
            url = new URL("https://rickandmortyapi.com/api/character/" + choice);
        } catch (MalformedURLException e) {
            log.error("Not correct URL");
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            log.error("Connection was not made");
        }
        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            log.error("Response was not received");
        }
        String body = null;
        try {
            body = new String(response.readAllBytes());
        } catch (IOException e) {
            log.error("Could not read response");
        }
        return body;
    }
}
