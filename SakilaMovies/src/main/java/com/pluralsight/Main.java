package com.pluralsight;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;
import com.pluralsight.utils.Console;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        if (args.length != 3) {
            System.out.println(
                "Application needs two arguments to run: " +
                "java com.pluralsight.UsingDriverManager <username> <password> <address>");
            System.exit(1);
        }

        // get the username and password from the command line args
        String username = args[0];
        String password = args[1];
        String sqlServerAddress = args[2];

        // load the MySQL Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load SQL driver");
        }

        // Create the BasicDataSource
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(sqlServerAddress); // Sakila
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // 1. open a connection to the database using DataSource
        try (Connection connection = dataSource.getConnection()) {

            String actor_lastname = Console.PromptForString("Enter actor last name: ");
            

            // define your query
            String lastname_query = String.format("""
                    SELECT
                        concat(
                            first_name
                            , " "
                            , last_name
                        ) AS full_name
                        
                    FROM sakila.actor
                    
                    WHERE last_name = ?;
                    """, actor_lastname); // The "?" is a placeholder for the PreparedStatement to prevent SQL Injection

            // Define statements
            try (PreparedStatement LastName_Statement = connection.prepareStatement(lastname_query);) {
                LastName_Statement.setString(1, actor_lastname);

                // 2. Execute your query
                try (ResultSet LastName = LastName_Statement.executeQuery();) {

                    // process the results
                    System.out.printf("%10s\n", "Full Name");
                    System.out.println("--------------------");

                    while (LastName.next()) {
                        String actor_name = LastName.getString("full_name");
                        
                        System.out.printf("%10s\n", actor_name);
                    }
                }
            }
        }
    }
}