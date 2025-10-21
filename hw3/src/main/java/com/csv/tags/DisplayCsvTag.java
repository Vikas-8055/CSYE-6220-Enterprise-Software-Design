package com.csv.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.*;

public class DisplayCsvTag extends SimpleTagSupport {
    
    private String filename;
    
    // Setter for the filename attribute
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // Get PageContext to access ServletContext
            PageContext pageContext = (PageContext) getJspContext();
            
            // Get the real path to the CSV file
            String csvPath = pageContext.getServletContext().getRealPath("/data/" + filename);
            
            // Load CsvJdbc driver
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            
            // Create connection to CSV file directory
            String csvDirectory = csvPath.substring(0, csvPath.lastIndexOf("/"));
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + csvDirectory);
            
            // Get filename without extension for table name
            String tableName = filename.replace(".csv", "");
            
            // Query the CSV file
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            
            // Get metadata to retrieve column names
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Start HTML with styling
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }");
            out.println(".container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); }");
            out.println("h2 { color: #2c3e50; text-align: center; margin-bottom: 20px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; font-size: 14px; }");
            out.println("th { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 12px; text-align: left; font-weight: 600; }");
            out.println("td { padding: 10px; border-bottom: 1px solid #e0e0e0; }");
            out.println("tr:nth-child(even) { background-color: #f8f9fa; }");
            out.println("tr:hover { background-color: #e9ecef; }");
            out.println(".record-count { text-align: center; color: #666; margin-top: 20px; font-style: italic; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>🅿️ Parking Facilities Data</h2>");
            out.println("<p style='text-align: center; color: #666;'>CSV File: <strong>" + filename + "</strong></p>");
            
            // Start table
            out.println("<table>");
            
            // Table header
            out.println("<thead><tr>");
            for (int i = 1; i <= columnCount; i++) {
                out.println("<th>" + metaData.getColumnName(i) + "</th>");
            }
            out.println("</tr></thead>");
            
            // Table body
            out.println("<tbody>");
            int recordCount = 0;
            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    out.println("<td>" + (value != null ? value : "") + "</td>");
                }
                out.println("</tr>");
                recordCount++;
            }
            out.println("</tbody>");
            out.println("</table>");
            
            // Display record count
            out.println("<p class='record-count'>Total Records: " + recordCount + "</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (ClassNotFoundException e) {
            out.println("<p style='color: red;'>Error: CsvJdbc driver not found!</p>");
            out.println("<p>" + e.getMessage() + "</p>");
        } catch (SQLException e) {
            out.println("<p style='color: red;'>Error reading CSV file: " + e.getMessage() + "</p>");
        } catch (Exception e) {
            out.println("<p style='color: red;'>Unexpected error: " + e.getMessage() + "</p>");
        }
    }
}