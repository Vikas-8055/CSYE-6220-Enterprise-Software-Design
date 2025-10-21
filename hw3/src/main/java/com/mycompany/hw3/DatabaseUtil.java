package com.mycompany.hw3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for database connections
 */
public class DatabaseUtil {
    
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/booksdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin@123";  // Your MySQL root password (empty if no password)
    
    /**
     * Get a connection to the database
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Return connection
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
    
    /**
     * Close database resources
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}