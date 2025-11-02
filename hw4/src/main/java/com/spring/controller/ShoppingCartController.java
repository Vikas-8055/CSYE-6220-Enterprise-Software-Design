package com.spring.controller;

import com.cart.model.Product;
import com.cart.model.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {
    
    // Sample products (in real app, would come from database)
    private List<Product> products;
    
    public ShoppingCartController() {
        products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 999.99, "Electronics"));
        products.add(new Product(2, "Mouse", 29.99, "Electronics"));
        products.add(new Product(3, "Keyboard", 79.99, "Electronics"));
        products.add(new Product(4, "Monitor", 299.99, "Electronics"));
        products.add(new Product(5, "Headphones", 149.99, "Electronics"));
        products.add(new Product(6, "Webcam", 89.99, "Electronics"));
    }
    
    @RequestMapping("/shop")
    public ModelAndView showProducts() {
        ModelAndView mav = new ModelAndView("productList");
        mav.addObject("products", products);
        return mav;
    }
    
    @RequestMapping("/addToCart")
    public ModelAndView addToCart(@RequestParam("productId") int productId,
                                   @RequestParam(value = "quantity", defaultValue = "1") int quantity,
                                   HttpSession session) {
        
        // Get cart from session (or create new)
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new HashMap<>();
        }
        
        // Find product
        Product product = findProductById(productId);
        
        if (product != null) {
            // Add or update cart item
            if (cart.containsKey(productId)) {
                CartItem item = cart.get(productId);
                item.setQuantity(item.getQuantity() + quantity);
            } else {
                cart.put(productId, new CartItem(product, quantity));
            }
        }
        
        session.setAttribute("cart", cart);
        
        return new ModelAndView("redirect:/viewCart");
    }
    
    @RequestMapping("/viewCart")
    public ModelAndView viewCart(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new HashMap<>();
        }
        
        double total = 0;
        for (CartItem item : cart.values()) {
            total += item.getSubtotal();
        }
        
        ModelAndView mav = new ModelAndView("cart");
        mav.addObject("cartItems", cart.values());
        mav.addObject("total", total);
        
        return mav;
    }
    
    @RequestMapping("/removeFromCart")
    public ModelAndView removeFromCart(@RequestParam("productId") int productId,
                                       HttpSession session) {
        
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        
        if (cart != null) {
            cart.remove(productId);
            session.setAttribute("cart", cart);
        }
        
        return new ModelAndView("redirect:/viewCart");
    }
    
    @RequestMapping("/checkout")
    public ModelAndView checkout(HttpSession session) {
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        
        int itemCount = cart != null ? cart.size() : 0;
        
        // Clear cart
        session.removeAttribute("cart");
        
        ModelAndView mav = new ModelAndView("checkout");
        mav.addObject("itemCount", itemCount);
        
        return mav;
    }
    
    private Product findProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}