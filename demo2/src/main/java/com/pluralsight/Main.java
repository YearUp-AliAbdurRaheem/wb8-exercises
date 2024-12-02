package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);
        }

        // get the username and password from the command line args
                String username = args[0];
                String password = args[1];


        // load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

// 1. open a connection to the database
// use the database URL to point to the correct database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila",
                    username,
                    password);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
// create statement
// the statement is tied to the open connection

        int countryID = 103;

        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM sakila.city WHERE country_id = ?;");
        pStatement.setInt(1, countryID);

// 2. Execute your query
        ResultSet results = pStatement.executeQuery();
// process the results
        while (results.next()) {
            String city = results.getString("city");
            System.out.println(city);
        }
// 3. Close the connection
        results.close();
        pStatement.close();
        connection.close();



    }
}