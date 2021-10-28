import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {

    public static void main(String[] args) throws IOException {
        String choice = args[0];

        URL url = new URL("https://rickandmortyapi.com/api/character/" + choice);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept", "application/json");
        InputStream response = connection.getInputStream();
        String body = new String(response.readAllBytes());
        System.out.println(body);
        //test

    }
}
