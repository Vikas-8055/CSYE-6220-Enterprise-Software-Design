package edu.neu.hw1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormBasicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirmPassword");
        String picture = req.getParameter("picture"); // just a name/filename for now
        String gender = req.getParameter("gender");
        String country = req.getParameter("country");
        String[] hobbies = req.getParameterValues("hobby");
        String address = req.getParameter("address");

        out.println("<html><body><h2>Form Parameters (getParameter / getParameterValues)</h2><table border='1'>");
        row(out, "Email", email);
        row(out, "Password", password);
        row(out, "Confirm Password", confirm);
        row(out, "Picture (name only)", picture);
        row(out, "Gender", gender);
        row(out, "Country", country);
        row(out, "Hobbies", join(hobbies));
        row(out, "Address", address);
        out.println("</table></body></html>");
    }

    private void row(PrintWriter out, String k, String v) {
        out.printf("<tr><td>%s</td><td>%s</td></tr>%n", esc(k), esc(v));
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

