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
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password);

            // create statement
            // the statement is tied to the open connection
                    Statement statement = connection.createStatement();
            // define your query
                    String query = "SELECT ProductName from Products;";
            // 2. Execute your query
                    ResultSet results = statement.executeQuery(query);
            // process the results
                    while (results.next()) {
                        String Product = results.getString("ProductName");
                        System.out.println(Product);
                    }
            // 3. Close the connection
                    connection.close();

        }
        finally {
        if (connection != null) {
            connection.close();
        }
        }
    }
}