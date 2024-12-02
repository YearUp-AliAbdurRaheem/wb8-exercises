package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        // load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

// 1. open a connection to the database
// use the database URL to point to the correct database
        Connection connection;
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sakila",
                "root",
                "yearup24");
// create statement
// the statement is tied to the open connection
        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM sakila.city WHERE country_id = 103;");

// 2. Execute your query
        ResultSet results = pStatement.executeQuery();
// process the results
        while (results.next()) {
            String city = results.getString("city");
            System.out.println(city);
        }
// 3. Close the connection
        connection.close();



    }
}