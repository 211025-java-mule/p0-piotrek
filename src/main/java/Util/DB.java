package Util;

import Model.Character;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DB {

    public void saveToDB(Character character) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql'", "sa", "");) {
            System.out.println("Connection made");
            String INSERT_INTO_CHARACTER = "insert into character(name, status, species, type, gender, origin, location, image, episode, url, created)  values (?, ? , ? , ? , ? , ? , ? ,? ,? ,? ,?);";


            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_CHARACTER);
            ps.setString(1, character.getName());
            ps.setString(2, character.getStatus());
            ps.setString(3, character.getSpecies());
            ps.setString(4, character.getType());
            ps.setString(5, character.getGender());
            ps.setString(6, String.valueOf(character.getOrigin()));
            ps.setString(7, String.valueOf(character.getLocation()));
            ps.setString(8, character.getImage());
            ps.setString(9, String.valueOf(character.getEpisode()));
            ps.setString(10, character.getUrl());
            ps.setString(11, character.getCreated());
            ps.executeUpdate();
            System.out.println("character added");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from character");

            while (resultSet.next()) {
                System.out.println("SQL DB: " + resultSet.getString("status"));
            }


        } catch (SQLException e) {
            log.error("SQL exeption"); // ???
        }
    }
}
