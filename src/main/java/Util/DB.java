package Util;

import Model.Character;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
@Slf4j
public class DB {

   public void saveToDB(Character character) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");) {

            Statement statement = conn.createStatement();
            statement.execute("create table if not exists character(name varchar(150))");
            statement.execute("insert into character(name) values ('" + character.getName() + "')");
            ResultSet resultSet = statement.executeQuery("select * from character");

            while (resultSet.next()) {
                System.out.println("SQL DB: " + resultSet.getString("name"));
            }


        } catch (SQLException e) {
            log.error("SQL exeption"); // ???
        }
    }
}
