package com.mycompany.hw2;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/shoppingCart"})
public class ShoppingCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get or create session FIRST (before security check)
        HttpSession session = request.getSession(true);
        
        // Get or create shopping cart
        @SuppressWarnings("unchecked")
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute("cart", cart);
        }
        
        // Get action parameter
        String action = request.getParameter("action");
        
        // If adding items, save them to session BEFORE security check
        if ("add".equals(action)) {
            String[] selectedItems = request.getParameterValues("item");
            if (selectedItems != null) {
                // Save pending items in session
                session.setAttribute("pendingItems", selectedItems);
            }
        }
        
        // PART 7: PROGRAMMATIC SECURITY
        // Check if user is logged in (session-based authentication)
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        
        if (username == null || !"customer".equals(role)) {
            // User not authenticated, redirect to login
            // Items are already saved in session as "pendingItems"
            session.setAttribute("redirectAfterLogin", "shoppingCart?action=processAdd");
            response.sendRedirect("login.jsp");
            return;
        }
        
        // User IS authenticated - process pending items if any
        String[] pendingItems = (String[]) session.getAttribute("pendingItems");
        if (pendingItems != null) {
            // Add pending items to cart
            for (String item : pendingItems) {
                String[] parts = item.split("-");
                if (parts.length >= 3) {
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    
                    if (cart.containsKey(id)) {
                        cart.get(id).incrementQuantity();
                    } else {
                        cart.put(id, new CartItem(id, name, price));
                    }
                }
            }
            // Clear pending items
            session.removeAttribute("pendingItems");
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Handle other actions
            if ("remove".equals(action)) {
                // REMOVE ITEM FROM CART
                String itemId = request.getParameter("itemId");
                if (itemId != null) {
                    cart.remove(itemId);
                }
                
            } else if ("clear".equals(action)) {
                // CLEAR ENTIRE CART
                cart.clear();
                
            } else if ("update".equals(action)) {
                // UPDATE QUANTITY
                updateQuantities(request, cart);
            }
            
            // DISPLAY CART
            displayCart(request, response, cart, session, out);
            
        } finally {
            out.close();
        }
    }
    
    /**
     * Update quantities for items in cart
     */
    private void updateQuantities(HttpServletRequest request, Map<String, CartItem> cart) {
        for (String itemId : cart.keySet()) {
            String quantityStr = request.getParameter("quantity_" + itemId);
            if (quantityStr != null) {
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity > 0) {
                        cart.get(itemId).setQuantity(quantity);
                    } else {
                        cart.remove(itemId);
                    }
                } catch (NumberFormatException e) {
                    // Invalid quantity, ignore
                }
            }
        }
    }
    
    /**
     * Display the shopping cart
     */
    private void displayCart(HttpServletRequest request, HttpServletResponse response,
                            Map<String, CartItem> cart, HttpSession session, PrintWriter out)
            throws IOException {
        
        // Start HTML
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Shopping Cart - Part 7</title>");
        out.println("<style>");
        out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; ");
        out.println("background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
        out.println("min-height: 100vh; padding: 20px; }");
        out.println(".container { max-width: 1200px; margin: 0 auto; background-color: white; ");
        out.println("padding: 30px; border-radius: 12px; box-shadow: 0 10px 40px rgba(0,0,0,0.2); }");
        out.println("h1 { color: #333; text-align: center; margin-bottom: 10px; }");
        out.println(".subtitle { text-align: center; color: #666; margin-bottom: 20px; font-size: 14px; }");
        out.println(".user-info { background-color: #e8f5e9; padding: 15px; border-radius: 8px; ");
        out.println("border-left: 4px solid #4caf50; margin-bottom: 20px; }");
        out.println(".session-info { background-color: #f0f4ff; padding: 15px; border-radius: 8px; ");
        out.println("border-left: 4px solid #667eea; margin-bottom: 20px; }");
        out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
        out.println("th { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); ");
        out.println("color: white; padding: 15px; text-align: left; font-weight: 600; }");
        out.println("td { border: 1px solid #e0e0e0; padding: 12px; }");
        out.println("tr:nth-child(even) { background-color: #f8f9fa; }");
        out.println("tr:hover { background-color: #f0f4ff; }");
        out.println(".empty-cart { text-align: center; padding: 40px; color: #999; }");
        out.println(".total-row { background-color: #fff3e0; font-weight: bold; font-size: 18px; }");
        out.println(".button { display: inline-block; padding: 10px 20px; margin: 5px; ");
        out.println("text-decoration: none; border-radius: 6px; font-weight: 600; transition: all 0.3s; }");
        out.println(".btn-primary { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
        out.println(".btn-danger { background: linear-gradient(135deg, #ff6b6b 0%, #ee5a6f 100%); color: white; }");
        out.println(".btn-success { background: linear-gradient(135deg, #51cf66 0%, #37b24d 100%); color: white; }");
        out.println(".button:hover { transform: translateY(-2px); box-shadow: 0 5px 15px rgba(0,0,0,0.2); }");
        out.println(".actions { text-align: center; margin: 20px 0; }");
        out.println("input[type='number'] { width: 60px; padding: 5px; border: 1px solid #ddd; border-radius: 4px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        
        // Display user info (Programmatic Security)
        String displayUsername = (String) session.getAttribute("username");
        if (displayUsername == null) {
            displayUsername = "Guest";
        }
        out.println("<div class='user-info'>");
        out.println("<strong>👤 Logged in as:</strong> " + escapeHtml(displayUsername));
        out.println(" | <strong>Role:</strong> customer");
        out.println("</div>");
        
        // Display session info (URL rewriting demo)
        out.println("<div class='session-info'>");
        out.println("<strong>🔒 Session ID:</strong> " + session.getId());
        out.println(" | <strong>Session Tracking:</strong> " + 
                   (request.isRequestedSessionIdFromCookie() ? "Cookies" : "URL Rewriting"));
        out.println("</div>");
        
        out.println("<h1>🛒 Your Shopping Cart</h1>");
        out.println("<p class='subtitle'>Part 7 - HttpSession + Programmatic Security + URL Rewriting</p>");
        
        if (cart.isEmpty()) {
            // Empty cart
            out.println("<div class='empty-cart'>");
            out.println("<h2>🛍️ Your cart is empty</h2>");
            out.println("<p>Start shopping and add items to your cart!</p>");
            out.println("</div>");
            
        } else {
            // Display cart items
            out.println("<form method='post' action='" + response.encodeURL("shoppingCart") + "'>");
            out.println("<input type='hidden' name='action' value='update' />");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Item</th>");
            out.println("<th>Price</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Subtotal</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");
            
            double grandTotal = 0.0;
            
            for (CartItem item : cart.values()) {
                double subtotal = item.getSubtotal();
                grandTotal += subtotal;
                
                // URL rewriting for session tracking
                String removeUrl = response.encodeURL("shoppingCart?action=remove&itemId=" + 
                                                     escapeHtml(item.getId()));
                
                out.println("<tr>");
                out.println("<td>" + escapeHtml(item.getName()) + "</td>");
                out.println("<td>$" + String.format("%.2f", item.getPrice()) + "</td>");
                out.println("<td>");
                out.println("<input type='number' name='quantity_" + escapeHtml(item.getId()) + 
                           "' value='" + item.getQuantity() + "' min='0' />");
                out.println("</td>");
                out.println("<td>$" + String.format("%.2f", subtotal) + "</td>");
                out.println("<td><a href='" + removeUrl + "' class='button btn-danger'>Remove</a></td>");
                out.println("</tr>");
            }
            
            // Total row
            out.println("<tr class='total-row'>");
            out.println("<td colspan='3'>Grand Total:</td>");
            out.println("<td colspan='2'>$" + String.format("%.2f", grandTotal) + "</td>");
            out.println("</tr>");
            
            out.println("</table>");
            out.println("<div class='actions'>");
            out.println("<button type='submit' class='button btn-success'>Update Quantities</button>");
            out.println("</div>");
            out.println("</form>");
        }
        
        // Action buttons
        out.println("<div class='actions'>");
        out.println("<a href='" + response.encodeURL("catalog.html") + "' class='button btn-primary'>Continue Shopping</a>");
        
        if (!cart.isEmpty()) {
            String clearUrl = response.encodeURL("shoppingCart?action=clear");
            out.println("<a href='" + clearUrl + "' class='button btn-danger' " +
                       "onclick='return confirm(\"Clear entire cart?\")'>Clear Cart</a>");
        }
        
        out.println("</div>");
        
        out.println("</div>"); // Close container
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * Escape HTML to prevent XSS attacks
     */
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
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
        return "Shopping Cart Servlet - Part 7: Sessions + Programmatic Security + URL Rewriting";
    }
}