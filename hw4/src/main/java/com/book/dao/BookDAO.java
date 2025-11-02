package com.book.dao;

import com.book.model.Book;
import com.book.util.BookDatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    
    public void addBooks(List<Book> books) {
        String sql = "INSERT INTO books (isbn, title, authors, price) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = BookDatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            for (Book book : books) {
                pstmt.setString(1, book.getIsbn());
                pstmt.setString(2, book.getTitle());
                pstmt.setString(3, book.getAuthors());
                pstmt.setFloat(4, book.getPrice());
                pstmt.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try (Connection conn = BookDatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthors(rs.getString("authors"));
                book.setPrice(rs.getFloat("price"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}