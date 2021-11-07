package com.revature.boczar.rickAndMorty;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.sql.*;

public class RickAndMortyTest {
    static Connection connection = null;

    @Before
    public void setUpConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql';MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkConnection() throws SQLException {
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

}
