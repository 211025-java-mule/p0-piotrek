
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class App {

    public static void main(String[] args){
        String choice = args[0];

        ObjectMapper objectMapper = new ObjectMapper();

        URL url = null;
        try {
            url = new URL("https://rickandmortyapi.com/api/character/" + choice);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = null;
        try {
            body = new String(response.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Character character = null;
        try {
            character = objectMapper.readValue(body, Character.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(character);

    }
}
