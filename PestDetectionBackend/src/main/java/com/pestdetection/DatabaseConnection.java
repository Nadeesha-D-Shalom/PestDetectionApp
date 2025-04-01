package com.pestdetection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Update these with your MySQL settings
    private static final String URL = "jdbc:mysql://localhost:3306/pest_detection_db"; // Change 'pest_detection_db' to your actual database name
    private static final String USER = "root";  // Change 'root' to your MySQL username
    private static final String PASSWORD = "Nadeesha@242002";  // Change 'password' to your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
