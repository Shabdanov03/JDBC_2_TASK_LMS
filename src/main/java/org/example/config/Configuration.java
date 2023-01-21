package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Shabdanov Ilim
 **/
public class Configuration {

//    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String userName = "postgres";
//    private static final String password = "postgres";
//
//    public static Connection getConnection(){
//        Connection connection = null;
//        try {
//          connection = DriverManager.getConnection(url,userName,password);
//            System.out.println("Successfully connected to database");
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//        return connection;
//    }
    public static Connection getConnection() throws SQLException{
        Connection connection = null;
        try {
           connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "postgres");
            System.out.println("Successfully connected to database !");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
