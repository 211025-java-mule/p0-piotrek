
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {

    public static void main(String[] args) throws IOException {
        String choice = args[0];

        ObjectMapper objectMapper = new ObjectMapper();

        URL url = new URL("https://rickandmortyapi.com/api/character/" + choice);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream response = connection.getInputStream();
        String body = new String(response.readAllBytes());

        Character character = objectMapper.readValue(body, Character.class);
        System.out.println(character);

    }
}
