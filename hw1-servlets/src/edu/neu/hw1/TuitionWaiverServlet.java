package edu.neu.hw1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TuitionWaiverServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Tuition Waiver Form Submission</h2><table border='1'>");
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] vals = req.getParameterValues(name);
            out.printf("<tr><td>%s</td><td>%s</td></tr>%n", esc(name), esc(join(vals)));
        }
        out.println("</table></body></html>");
    }

    private String join(String[] vals) {
        if (vals == null || vals.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vals.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(vals[i]);
        }
        return sb.toString();
    }
    private String esc(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }
}
