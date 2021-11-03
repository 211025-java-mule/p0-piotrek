
import Model.Character;
import Util.Connection;
import Util.UI;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        String choice = args[0];
        Connection connection = new Connection();
        UI ui = new UI();
        ObjectMapper objectMapper = new ObjectMapper();

        String body = connection.getBody(choice);
        Character character = ui.getCharacter(objectMapper, body);
        System.out.println(character);
    }
}
