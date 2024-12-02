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


            // define your query
                    String ProductIDs_Query = "SELECT ProductID from Products;";
                    String ProductNames_Query = "SELECT ProductName from Products;";
                    String UnitPrices_Query = "SELECT UnitPrice from Products;";
                    String UnitsInStock_Query = "SELECT UnitsInStock from Products;";

            // Define statements
                    PreparedStatement ProductIDs_Statement = connection.prepareStatement(ProductIDs_Query);
                    PreparedStatement ProductNames_Statement = connection.prepareStatement(ProductNames_Query);
                    PreparedStatement UnitPrices_Statement = connection.prepareStatement(UnitPrices_Query);
                    PreparedStatement UnitsInStock_Statement = connection.prepareStatement(UnitsInStock_Query);


            // 2. Execute your query
                    ResultSet ProductIDs = ProductIDs_Statement.executeQuery();
                    ResultSet ProductNames = ProductNames_Statement.executeQuery();
                    ResultSet UnitPrices = UnitPrices_Statement.executeQuery();
                    ResultSet UnitsInStock = UnitsInStock_Statement.executeQuery();
            // process the results


            // 3. Close the connection
                    ProductIDs.close();
                    ProductNames.close();
                    UnitPrices.close();
                    UnitsInStock.close();

                    ProductIDs_Statement.close();
                    ProductNames_Statement.close();
                    UnitPrices_Statement.close();
                    UnitsInStock_Statement.close();

                    connection.close();

        }
        finally {
        if (connection != null) {
            connection.close();
        }
        }
    }
}