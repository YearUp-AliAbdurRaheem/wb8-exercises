package com.pluralsight;

import java.sql.*;

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

        // 1. open a connection to the database
        // use the database URL to point to the correct database
        try (Connection connection = DriverManager.getConnection(sqlServerAddress, username, password)) {
            // define your query
            String ProductIDs_Query = "SELECT ProductID from Products;";
            String ProductNames_Query = "SELECT ProductName from Products;";
            String UnitPrices_Query = "SELECT UnitPrice from Products;";
            String UnitsInStock_Query = "SELECT UnitsInStock from Products;";

            // Define statements
            try (PreparedStatement ProductIDs_Statement = connection.prepareStatement(ProductIDs_Query);
                 PreparedStatement ProductNames_Statement = connection.prepareStatement(ProductNames_Query);
                 PreparedStatement UnitPrices_Statement = connection.prepareStatement(UnitPrices_Query);
                 PreparedStatement UnitsInStock_Statement = connection.prepareStatement(UnitsInStock_Query)) {

                // 2. Execute your query
                try (ResultSet ProductIDs = ProductIDs_Statement.executeQuery();
                     ResultSet ProductNames = ProductNames_Statement.executeQuery();
                     ResultSet UnitPrices = UnitPrices_Statement.executeQuery();
                     ResultSet UnitsInStock = UnitsInStock_Statement.executeQuery()) {

                    // process the results
                    System.out.printf("%-20s %-22s %-6s %-6s%n", "Id", "Name", "Price", "Stock");
                    System.out.println("-- --------------------------------------- ------- -----");

                    while (ProductIDs.next() && ProductNames.next() && UnitPrices.next() && UnitsInStock.next()) {
                        int id = ProductIDs.getInt("ProductID");
                        String name = ProductNames.getString("ProductName");
                        double price = UnitPrices.getDouble("UnitPrice");
                        int stock = UnitsInStock.getInt("UnitsInStock");
                        
                        System.out.printf("%-2d %-40s %-7.2f %-6d%n", id, name, price, stock);
                    }
                }
            }
        }
    }
}