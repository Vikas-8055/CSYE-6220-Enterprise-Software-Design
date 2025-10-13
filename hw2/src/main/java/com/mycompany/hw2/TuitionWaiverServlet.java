package com.mycompany.hw2;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "TuitionWaiverServlet", urlPatterns = {"/processTuition"})
public class TuitionWaiverServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Start HTML with styling
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Tuition Waiver Submission - Part 6</title>");
            out.println("<style>");
            out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
            out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; ");
            out.println("background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
            out.println("min-height: 100vh; padding: 20px; }");
            out.println(".container { max-width: 1200px; margin: 0 auto; background-color: white; ");
            out.println("padding: 30px; border-radius: 12px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); }");
            out.println("h2 { color: #333; text-align: center; margin-bottom: 10px; }");
            out.println(".subtitle { text-align: center; color: #666; margin-bottom: 30px; font-size: 14px; }");
            out.println(".success { color: #2e7d32; background-color: #e8f5e9; padding: 15px; ");
            out.println("border-radius: 8px; border-left: 4px solid #4caf50; margin-bottom: 20px; text-align: center; }");
            out.println(".param-box { background-color: #f0f4ff; border-left: 4px solid #667eea; ");
            out.println("padding: 15px; margin: 20px 0; border-radius: 4px; }");
            out.println(".param-box h3 { color: #667eea; margin-bottom: 15px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; ");
            out.println("box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; }");
            out.println("th { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
            out.println("color: white; padding: 15px; text-align: left; font-weight: 600; }");
            out.println("td { border: 1px solid #e0e0e0; padding: 12px; }");
            out.println("td:first-child { font-weight: 600; width: 35%; background-color: #f8f9fa; }");
            out.println("tr:hover { background-color: #f0f4ff; transition: background-color 0.3s; }");
            out.println(".back-link { display: inline-block; margin: 20px 0; padding: 12px 24px; ");
            out.println("background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
            out.println("color: white; text-decoration: none; border-radius: 6px; ");
            out.println("transition: transform 0.2s, box-shadow 0.2s; }");
            out.println(".back-link:hover { transform: translateY(-2px); ");
            out.println("box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4); }");
            out.println(".summary { background-color: #fff3e0; border-left: 4px solid #ff9800; ");
            out.println("padding: 15px; margin: 20px 0; border-radius: 4px; }");
            out.println(".summary strong { color: #ff9800; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            // Success message
            out.println("<div class='success'>");
            out.println("<strong>✓ Form Submitted Successfully!</strong>");
            out.println("<p>Your tuition waiver application has been received and will be processed.</p>");
            out.println("</div>");
            
            out.println("<h2>📋 Tuition Waiver Form Submission</h2>");
            out.println("<p class='subtitle'>Part 6 - Using @WebServlet Annotations, getParameterNames(), and Declarative Security</p>");
            
            // CRITICAL PART 6 REQUIREMENT: Display parameter count using getParameterNames()
            out.println("<div class='param-box'>");
            out.println("<h3>📊 Request Parameters Analysis (using getParameterNames())</h3>");
            
            // Count parameters
            int paramCount = 0;
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                paramNames.nextElement();
                paramCount++;
            }
            
            out.println("<p><strong>Total Form Fields Received:</strong> " + paramCount + " parameters</p>");
            out.println("<p><strong>HTTP Method:</strong> " + request.getMethod() + "</p>");
            out.println("<p><strong>Content Type:</strong> " + request.getContentType() + "</p>");
            out.println("</div>");
            
            // Display all parameters in a table using getParameterNames()
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Parameter Name</th>");
            out.println("<th>Value(s)</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
            // Use getParameterNames() to iterate through all parameters
            Enumeration<String> names = request.getParameterNames();
            int rowNum = 0;
            
            while (names.hasMoreElements()) {
                rowNum++;
                String paramName = names.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                
                out.println("<tr>");
                out.println("<td>" + escapeHtml(paramName) + "</td>");
                out.println("<td>" + escapeHtml(joinValues(paramValues)) + "</td>");
                out.println("</tr>");
            }
            
            if (rowNum == 0) {
                out.println("<tr>");
                out.println("<td colspan='2' style='text-align: center; color: #999;'>No parameters received</td>");
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            
            // Summary
            out.println("<div class='summary'>");
            out.println("<strong>📈 Processing Summary:</strong> ");
            out.println("Successfully processed " + paramCount + " form fields from the tuition waiver application. ");
            out.println("This servlet uses <strong>@WebServlet annotation</strong> for mapping, ");
            out.println("<strong>getParameterNames()</strong> for parameter iteration, ");
            out.println("and <strong>Declarative Security</strong> for authentication.");
            out.println("</div>");
            
            // Back link
            out.println("<a href='tuitionWaiver.html' class='back-link'>← Submit Another Application</a>");
            
            out.println("</div>"); // Close container
            out.println("</body>");
            out.println("</html>");
            
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to the form
        response.sendRedirect("tuitionWaiver.html");
    }

    /**
     * Helper method to join multiple values with commas
     * Used for checkbox groups and multi-select fields
     */
    private String joinValues(String[] values) {
        if (values == null || values.length == 0) {
            return "<em>(empty)</em>";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(values[i]);
        }
        return sb.toString();
    }

    /**
     * Helper method to escape HTML special characters
     * Prevents XSS attacks and display issues
     */
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }

    @Override
    public String getServletInfo() {
        return "Tuition Waiver Servlet - Part 6: Annotations + getParameterNames() + Declarative Security";
    }
}