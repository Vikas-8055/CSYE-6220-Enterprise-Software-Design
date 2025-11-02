package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookDatabaseUtil {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/booksdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin@123";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
    }
    
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