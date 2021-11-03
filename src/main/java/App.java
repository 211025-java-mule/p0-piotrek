
import Model.Character;
import Util.ApiConnection;
import Util.DB;
import Util.UI;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class App {
    public static void main(String[] args) {
        String choice = args[0];
        ApiConnection connection = new ApiConnection();
        UI ui = new UI();
        ObjectMapper objectMapper = new ObjectMapper();
        DB db = new DB();

        String body = connection.getBody(choice);
        Character character = ui.getCharacter(objectMapper, body);
        System.out.println(character);

        //DB
        db.saveToDB(character);

    }
}
