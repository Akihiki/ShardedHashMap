package org.example.repository;

import org.example.Constants;

import java.sql.*;

public class DbConnection {

     static Connection connection;
   static Connection connection2;

    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
            connection2 = DriverManager.getConnection(Constants.url2, Constants.username, Constants.password);

            if (connection != null) {
                System.out.println("Connected to the first database ");
                //connection.close();
            } else {
                System.out.println("Failed to make connection to database 1!");
            }
            if (connection2 != null) {
                System.out.println("Connected to the second database ");
                //connection.close();
            } else {
                System.out.println("Failed to make connection to database 2!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find PostgreSQL JDBC Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public static Connection connectionResolver(String key){
        if(key.hashCode()%2==0)
            return connection2;
        else
            return connection;
    }


}
