package com.revature.boczar.rickAndMorty.Util;

import com.revature.boczar.rickAndMorty.Model.Character;
import lombok.extern.slf4j.Slf4j;
import tech.tablesaw.api.Table;

import java.sql.*;

@Slf4j
public class CharacterPostgresRepository {

    /**
     * This method saves character to PSQL database placed in Docker container.
     * @param character Method accepts object of Character class as an input.
     *
     */
    public void saveCharacterToDB(Character character) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password")) {

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

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * This method print all entries in 'character' table in PSQL database.
     */
    public void printAllCharacters(){

        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password")){
            String GET_ALL_CHARACTERS = "select * from character";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_CHARACTERS);
            System.out.println(Table.read().db(resultSet).print());

        } catch (SQLException e){
            log.error(e.getMessage());
        }
    }
}
