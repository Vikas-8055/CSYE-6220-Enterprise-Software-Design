package edu.neu.hw1;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestIntrospectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!doctype html><html><head><meta charset='utf-8'>");
        out.println("<title>Request Introspection</title>");
        out.println("<style>");
        out.println("body{font-family:system-ui,Segoe UI,Arial;margin:18px;}");
        out.println("h1{margin:0 0 10px} h2{margin:24px 0 8px}");
        out.println("table{border-collapse:collapse;width:100%;max-width:980px}");
        out.println("th,td{border:1px solid #ccc;padding:6px 8px;vertical-align:top}");
        out.println("th{background:#f6f8fa;text-align:left;width:280px}");
        out.println("code{color:#a31515}");
        out.println("</style></head><body>");
        out.println("<h1>All <code>getX()</code> Methods</h1>");

        /* ---------------- HttpServletRequest-specific ---------------- */
        out.println("<h2>HttpServletRequest Methods</h2>");
        out.println("<table>");
        printRow(out, "getAuthType()", req.getAuthType());
        printRow(out, "getContextPath()", req.getContextPath());

        // Cookies (joined)
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Cookie c : cookies) {
                if (sb.length() > 0) sb.append("; ");
                sb.append(c.getName()).append('=').append(c.getValue());
            }
            printRow(out, "getCookies()", sb.toString());
        } else {
            printRow(out, "getCookies()", "—");
        }

        // Leave these 2 examples if you still want them; they are NOT the big table.
        printRow(out, "getHeader(\"User-Agent\")", req.getHeader("User-Agent"));
        printRow(out, "getHeader(\"Accept\")", req.getHeader("Accept"));
        printRow(out, "getIntHeader(\"Content-Length\")", String.valueOf(req.getIntHeader("Content-Length")));
        printRow(out, "getDateHeader(\"If-Modified-Since\")", String.valueOf(req.getDateHeader("If-Modified-Since")));

        printRow(out, "getMethod()", req.getMethod());
        printRow(out, "getPathInfo()", req.getPathInfo());
        printRow(out, "getPathTranslated()", req.getPathTranslated());
        printRow(out, "getQueryString()", req.getQueryString());
        printRow(out, "getRemoteUser()", req.getRemoteUser());
        printRow(out, "getRequestedSessionId()", req.getRequestedSessionId());
        printRow(out, "getRequestURI()", req.getRequestURI());
        printRow(out, "getRequestURL()", req.getRequestURL() == null ? null : req.getRequestURL().toString());
        printRow(out, "getServletPath()", req.getServletPath());

        // Session-related
        HttpSession session = req.getSession(false);
        printRow(out, "getSession(false).getId()", session == null ? "—" : session.getId());
        printRow(out, "isRequestedSessionIdFromCookie()", String.valueOf(req.isRequestedSessionIdFromCookie()));
        printRow(out, "isRequestedSessionIdFromURL()", String.valueOf(req.isRequestedSessionIdFromURL()));
        printRow(out, "isRequestedSessionIdValid()", String.valueOf(req.isRequestedSessionIdValid()));

        Principal p = req.getUserPrincipal();
        printRow(out, "getUserPrincipal()", p == null ? "—" : p.getName());
        out.println("</table>");

        /* ------------- REMOVED: the big “Headers” table -------------
        // (Deleted block that printed all headers with getHeaderNames()/getHeaders())
        --------------------------------------------------------------- */

        /* ---------------- ServletRequest (inherited) ---------------- */
        out.println("<h2>ServletRequest Methods (Inherited)</h2>");
        out.println("<table>");
        printRow(out, "getCharacterEncoding()", req.getCharacterEncoding());
        printRow(out, "getContentLength()", String.valueOf(req.getContentLength()));
        printRow(out, "getContentLengthLong()", String.valueOf(req.getContentLengthLong()));
        printRow(out, "getContentType()", req.getContentType());
        printRow(out, "getLocalAddr()", req.getLocalAddr());
        printRow(out, "getLocalName()", req.getLocalName());
        printRow(out, "getLocalPort()", String.valueOf(req.getLocalPort()));

        // Locale(s)
        Locale primary = req.getLocale();
        StringBuilder locales = new StringBuilder(primary == null ? "" : primary.toString());
        Enumeration<Locale> locs = req.getLocales();
        boolean first = true;
        while (locs != null && locs.hasMoreElements()) {
            Locale L = locs.nextElement();
            if (first) { first = false; continue; } // first is same as getLocale()
            if (!locales.isEmpty()) locales.append(", ");
            locales.append(L.toString());
        }
        printRow(out, "getLocale()", primary == null ? "—" : primary.toString());
        printRow(out, "getLocales()", locales.length() == 0 ? "—" : locales.toString());

        // (Skipping getParameter* on purpose.)
        printRow(out, "getProtocol()", req.getProtocol());
        printRow(out, "getRemoteAddr()", req.getRemoteAddr());
        printRow(out, "getRemoteHost()", req.getRemoteHost());
        printRow(out, "getRemotePort()", String.valueOf(req.getRemotePort()));
        printRow(out, "getScheme()", req.getScheme());
        printRow(out, "getServerName()", req.getServerName());
        printRow(out, "getServerPort()", String.valueOf(req.getServerPort()));

        ServletContext sc = req.getServletContext();
        printRow(out, "getServletContext().getContextPath()", sc == null ? "—" : sc.getContextPath());
        printRow(out, "getServletContext().getServerInfo()", sc == null ? "—" : sc.getServerInfo());

        printRow(out, "isSecure()", String.valueOf(req.isSecure()));
        out.println("</table>");

        out.println("</body></html>");
    }

    /* ---------- helpers ---------- */
    private static void printRow(PrintWriter out, String label, String value) {
        out.println("<tr><th><code>" + escape(label) + "</code></th><td>"
                + (value == null || value.isEmpty() ? "—" : escape(value)) + "</td></tr>");
    }

    private static String escape(String s) {
        return s == null ? "" :
                s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
