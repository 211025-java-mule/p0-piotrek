
import Model.Character;
import Util.ApiConnection;
import Util.DB;
import Util.UI;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class App {
    public static void main(String[] args) {
        ApiConnection apiConnection = new ApiConnection();
        UI ui = new UI();
        ObjectMapper objectMapper = new ObjectMapper();
        DB db = new DB();

        while (true) {
            System.out.println("Welcome to Rick & Morty App! Please pick number from 1 to 826 to retrieve information about Rick & Morty character! ");
            Scanner scanner = new Scanner(System.in);
            if (!(scanner.hasNextInt())) {
                System.out.println("Please input an integer");
            } else {

            int usersChoice = scanner.nextInt();

            if (usersChoice == 0) {
                System.out.println("Bye bye!");
                System.exit(0);
            }
            String body = apiConnection.getBody(usersChoice);
            Character character = ui.getCharacter(objectMapper, body);

            //DB
            db.saveCharacterToDB(character);
        }
    }

    }


}


