package bank.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class dbConnection {

    Connection connection;
    Statement statement;

    dbConnection() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankSystem?useSSL=false&serverTimezone=UTC",
                    "root",
                    ""
            );

            // Create statement
            statement = connection.createStatement();

            System.out.println("Database connected successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
