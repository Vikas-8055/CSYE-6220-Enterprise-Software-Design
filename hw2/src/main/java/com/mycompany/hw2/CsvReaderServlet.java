package com.mycompany.hw2;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "CsvReaderServlet", urlPatterns = {"*.xls"})
public class CsvReaderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            String filename = request.getParameter("filename");
            
            // Start HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CSV Data - " + filename + "</title>");
            out.println("<style>");
            out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
            out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; ");
            out.println("background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
            out.println("min-height: 100vh; padding: 20px; }");
            out.println(".container { max-width: 1400px; margin: 0 auto; background-color: white; ");
            out.println("padding: 30px; border-radius: 12px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); }");
            out.println("h2 { color: #333; text-align: center; margin-bottom: 10px; }");
            out.println(".subtitle { text-align: center; color: #666; margin-bottom: 30px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; ");
            out.println("box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px; overflow: hidden; }");
            out.println("th { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
            out.println("color: white; padding: 15px; text-align: left; font-weight: 600; }");
            out.println("td { border: 1px solid #e0e0e0; padding: 12px; }");
            out.println("tr:nth-child(even) { background-color: #f8f9fa; }");
            out.println("tr:hover { background-color: #f0f4ff; transition: background-color 0.3s; }");
            out.println(".back-link { display: inline-block; margin: 20px 0; padding: 12px 24px; ");
            out.println("background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
            out.println("color: white; text-decoration: none; border-radius: 6px; ");
            out.println("transition: transform 0.2s, box-shadow 0.2s; }");
            out.println(".back-link:hover { transform: translateY(-2px); ");
            out.println("box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4); }");
            out.println(".error { color: #d32f2f; background-color: #ffebee; padding: 20px; ");
            out.println("border-radius: 8px; border-left: 4px solid #d32f2f; margin: 20px 0; }");
            out.println(".success { color: #2e7d32; background-color: #e8f5e9; padding: 15px; ");
            out.println("border-radius: 8px; border-left: 4px solid #4caf50; margin-bottom: 20px; }");
            out.println(".summary { background-color: #f0f4ff; padding: 15px; border-radius: 8px; ");
            out.println("margin: 20px 0; border-left: 4px solid #667eea; }");
            out.println(".summary strong { color: #667eea; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            // Validate filename
            if (filename == null || filename.trim().isEmpty()) {
                out.println("<div class='error'>");
                out.println("<h3>❌ Error: No filename provided</h3>");
                out.println("<p>Please enter a CSV filename.</p>");
                out.println("</div>");
                out.println("<a href='index.html' class='back-link'>← Go Back</a>");
                out.println("</div></body></html>");
                return;
            }
            
            // Load CsvJdbc driver
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            
            // Get path to CSV directory
            String csvDirectory = getServletContext().getRealPath("/WEB-INF/data/");
            
            // Connect to CSV directory
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + csvDirectory);
            
            // Remove .csv extension for table name
            String tableName = filename.replace(".csv", "");
            
            // Execute query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            
            // Get metadata
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            // Success message
            out.println("<div class='success'>");
            out.println("<strong>✓ Success!</strong> CSV file loaded successfully using CsvJdbc driver on Tomcat 10");
            out.println("</div>");
            
            out.println("<h2>📊 " + filename + "</h2>");
            out.println("<p class='subtitle'>Data retrieved using CsvJdbc JDBC Driver</p>");
            
            // Start table
            out.println("<table>");
            
            // Table headers
            out.println("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                out.println("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.println("</tr>");
            
            // Table data
            int rowCount = 0;
            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    out.println("<td>" + (value != null ? value : "") + "</td>");
                }
                out.println("</tr>");
                rowCount++;
            }
            
            out.println("</table>");
            
            // Summary
            out.println("<div class='summary'>");
            out.println("<strong>📈 Summary:</strong> ");
            out.println(columnCount + " columns × " + rowCount + " rows = " + (columnCount * rowCount) + " data cells");
            out.println("</div>");
            
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
            
            // Back link
            out.println("<a href='index.html' class='back-link'>← Read Another CSV File</a>");
            
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'>");
            out.println("<h3>❌ CSV JDBC Driver Not Found</h3>");
            out.println("<p><strong>Error:</strong> " + e.getMessage() + "</p>");
            out.println("<p>Make sure csvjdbc library is in your dependencies.</p>");
            out.println("</div>");
            out.println("<a href='index.html' class='back-link'>← Go Back</a>");
            
        } catch (SQLException e) {
            out.println("<div class='error'>");
            out.println("<h3>❌ Database Error</h3>");
            out.println("<p><strong>Error:</strong> " + e.getMessage() + "</p>");
            out.println("<h4>Common Causes:</h4>");
            out.println("<ul>");
            out.println("<li>File not found in WEB-INF/data/ directory</li>");
            out.println("<li>Incorrect filename (check spelling and .csv extension)</li>");
            out.println("<li>Invalid CSV format or corrupted file</li>");
            out.println("<li>SQL syntax error in query</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("<a href='index.html' class='back-link'>← Go Back</a>");
            
        } catch (Exception e) {
            out.println("<div class='error'>");
            out.println("<h3>❌ Unexpected Error</h3>");
            out.println("<p><strong>Error:</strong> " + e.getMessage() + "</p>");
            out.println("</div>");
            out.println("<a href='index.html' class='back-link'>← Go Back</a>");
            
        } finally {
            out.println("</div>"); // Close container
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Check if filename parameter exists
        String filename = request.getParameter("filename");
        
        // If no filename provided (e.g., when run directly), redirect to form
        if (filename == null || filename.trim().isEmpty()) {
            response.sendRedirect("index.html");
        } else {
            // Filename provided, process normally
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "CSV File Reader using CsvJdbc - Tomcat 10 Compatible";
    }
}