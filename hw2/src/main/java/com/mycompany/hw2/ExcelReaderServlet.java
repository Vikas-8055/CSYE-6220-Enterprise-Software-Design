package com.mycompany.hw2;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

@WebServlet(name = "ExcelReaderServlet", urlPatterns = {"/readexcel.xls"})
public class ExcelReaderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Start HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Excel Data - store.xls</title>");
            out.println("<style>");
            out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
            out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; ");
            out.println("background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
            out.println("min-height: 100vh; padding: 20px; }");
            out.println(".container { max-width: 1400px; margin: 0 auto; background-color: white; ");
            out.println("padding: 30px; border-radius: 12px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); }");
            out.println("h2 { color: #333; text-align: center; margin-bottom: 10px; }");
            out.println(".subtitle { text-align: center; color: #666; margin-bottom: 30px; font-size: 14px; }");
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
            
            // Get the path to the Excel file
            String filePath = getServletContext().getRealPath("/WEB-INF/data/store.xls");
            File excelFile = new File(filePath);
            
            // Check if file exists
            if (!excelFile.exists()) {
                out.println("<div class='error'>");
                out.println("<h3>❌ Error: File Not Found</h3>");
                out.println("<p>store.xls not found in WEB-INF/data/ directory</p>");
                out.println("<p>Path checked: " + filePath + "</p>");
                out.println("</div>");
                out.println("<a href='index.html' class='back-link'>← Go Back</a>");
                out.println("</div></body></html>");
                return;
            }
            
            // Read Excel file
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook workbook = new HSSFWorkbook(fis);
            
            // Get first sheet
            Sheet sheet = workbook.getSheetAt(0);
            
            // Success message
            out.println("<div class='success'>");
            out.println("<strong>✓ Success!</strong> Excel file loaded successfully using Apache POI on Tomcat 10");
            out.println("</div>");
            
            out.println("<h2>📊 store.xls</h2>");
            out.println("<p class='subtitle'>Data retrieved using Apache POI Library</p>");
            
            // Start table
            out.println("<table>");
            
            boolean firstRow = true;
            int rowCount = 0;
            int columnCount = 0;
            
            // Iterate through rows
            for (Row row : sheet) {
                out.println("<tr>");
                
                if (firstRow) {
                    columnCount = row.getPhysicalNumberOfCells();
                }
                
                // Iterate through cells
                for (Cell cell : row) {
                    String cellValue = getCellValue(cell);
                    
                    if (firstRow) {
                        out.println("<th>" + cellValue + "</th>");
                    } else {
                        out.println("<td>" + cellValue + "</td>");
                    }
                }
                
                out.println("</tr>");
                
                if (!firstRow) {
                    rowCount++;
                }
                firstRow = false;
            }
            
            out.println("</table>");
            
            // Summary
            out.println("<div class='summary'>");
            out.println("<strong>📈 Summary:</strong> ");
            out.println(columnCount + " columns × " + rowCount + " data rows");
            out.println("</div>");
            
            // Close resources
            workbook.close();
            fis.close();
            
            // Back link
            out.println("<a href='index.html' class='back-link'>← Go to Home</a>");
            
        } catch (FileNotFoundException e) {
            out.println("<div class='error'>");
            out.println("<h3>❌ File Not Found</h3>");
            out.println("<p><strong>Error:</strong> " + e.getMessage() + "</p>");
            out.println("</div>");
            out.println("<a href='index.html' class='back-link'>← Go Back</a>");
            
        } catch (IOException e) {
            out.println("<div class='error'>");
            out.println("<h3>❌ Error Reading Excel File</h3>");
            out.println("<p><strong>Error:</strong> " + e.getMessage() + "</p>");
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
    
    // Helper method to get cell value as String
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                // Format numbers to avoid scientific notation
                double numValue = cell.getNumericCellValue();
                if (numValue == (long) numValue) {
                    return String.format("%d", (long) numValue);
                } else {
                    return String.format("%.2f", numValue);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Excel File Reader using Apache POI - Tomcat 10 Compatible";
    }
}