package com.mycompany.hw2;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "LoginProcessServlet", urlPatterns = {"/loginProcess"})
public class LoginProcessServlet extends HttpServlet {

    // Hardcoded credentials for demo (in real app, use database)
    private static final String VALID_USERNAME = "customer";
    private static final String VALID_PASSWORD = "shop123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate credentials
        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            // Login successful
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("role", "customer");
            
            // Redirect to shopping cart or catalog
            String redirectTo = (String) session.getAttribute("redirectAfterLogin");
            if (redirectTo != null) {
                session.removeAttribute("redirectAfterLogin");
                response.sendRedirect(redirectTo);
            } else {
                response.sendRedirect("catalog.html");
            }
            
        } else {
            // Login failed
            response.sendRedirect("login.jsp?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Login Processing Servlet - Part 7 Programmatic Security";
    }
}