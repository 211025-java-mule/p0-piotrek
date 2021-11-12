package com.revature.boczar.rickAndMorty;

import com.revature.boczar.rickAndMorty.Util.ApplicationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;


import static org.junit.Assert.assertEquals;


public class RickAndMortyDBTest {
    static Connection connection;
    static ApplicationContext applicationContext;

    @Before
    public void setUpConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        applicationContext = new ApplicationContext();
    }

    @Test
    public void checkConnection() {
        Assert.assertNotEquals(connection, null);
    }

    @Test
    public void testNumberOfColumnsInDB() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from character;");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        assertEquals(12, resultSetMetaData.getColumnCount());
    }

    @Test
    public void checkIfInsertingWorksProperly() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("insert into character (name) values ('Rick');");
        ResultSet resultSet = statement.executeQuery("select * from character;");

        while (resultSet.next()) {
            assertEquals("Rick", resultSet.getString("name"));
        }
    }

    @Test
    public void testConnectionToAPI() throws IOException {
        URL url = new URL("https://rickandmortyapi.com/api/character/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Assert.assertNotEquals(null, connection);
    }

    @Test
    public void testResponseFromAPI() throws IOException {
        URL url = new URL("https://rickandmortyapi.com/api/character/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream response = connection.getInputStream();
        String body = new String(response.readAllBytes());
        Assert.assertNotEquals(null, body);
    }
}
