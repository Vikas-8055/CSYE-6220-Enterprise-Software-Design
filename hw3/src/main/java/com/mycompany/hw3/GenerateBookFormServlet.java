package com.mycompany.hw3;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "GenerateBookFormServlet", urlPatterns = {"/generateBookForm"})
public class GenerateBookFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the number of books from form
        String numBooksStr = request.getParameter("numBooks");
        int numBooks = Integer.parseInt(numBooksStr);
        
        // Store in session
        HttpSession session = request.getSession();
        session.setAttribute("numBooks", numBooks);
        
        // Forward to the add books JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addBooks.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("askQuantity.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Generate Book Form Servlet - Part 7";
    }
}