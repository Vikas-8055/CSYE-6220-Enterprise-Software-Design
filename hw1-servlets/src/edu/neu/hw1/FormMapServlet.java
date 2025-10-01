package edu.neu.hw1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormMapServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Form Parameters (getParameterMap)</h2><ul>");
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            out.println("<li>" + key + ": ");
            for (String value : params.get(key)) {
                out.println(value + " ");
            }
            out.println("</li>");
        }
        out.println("</ul></body></html>");
    }
}