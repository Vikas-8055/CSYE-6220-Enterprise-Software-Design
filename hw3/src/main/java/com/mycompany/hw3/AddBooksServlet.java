package com.mycompany.hw3;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "AddBooksServlet", urlPatterns = {"/addBooks"})
public class AddBooksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer numBooks = (Integer) session.getAttribute("numBooks");
        
        if (numBooks == null) {
            response.sendRedirect("askQuantity.jsp");
            return;
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        int booksAdded = 0;
        
        try {
            // Get database connection
            conn = DatabaseUtil.getConnection();
            
            // PART 7 REQUIREMENT: Use PreparedStatement
            String sql = "INSERT INTO books (isbn, title, authors, price) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            // Insert each book using PreparedStatement
            for (int i = 0; i < numBooks; i++) {
                String isbn = request.getParameter("isbn_" + i);
                String title = request.getParameter("title_" + i);
                String authors = request.getParameter("authors_" + i);
                String priceStr = request.getParameter("price_" + i);
                
                if (isbn != null && title != null && authors != null && priceStr != null) {
                    float price = Float.parseFloat(priceStr);
                    
                    // Set parameters for PreparedStatement
                    pstmt.setString(1, isbn);
                    pstmt.setString(2, title);
                    pstmt.setString(3, authors);
                    pstmt.setFloat(4, price);
                    
                    // Execute insert
                    pstmt.executeUpdate();
                    booksAdded++;
                }
            }
            
            // Store count in request for confirmation page
            request.setAttribute("booksAdded", booksAdded);
            
            // Forward to confirmation page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/booksAdded.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        } finally {
            // Close resources
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseUtil.closeConnection(conn);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("askQuantity.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Add Books Servlet using PreparedStatement - Part 7";
    }
}