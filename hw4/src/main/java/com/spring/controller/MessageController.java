package com.spring.controller;

import com.spring.util.MessageUtility;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MessageController implements Controller {
    
    // This will be injected by Spring (NOT created with 'new')
    private MessageUtility messageUtility;
    
    // Setter for Spring to inject the dependency
    public void setMessageUtility(MessageUtility messageUtility) {
        this.messageUtility = messageUtility;
        System.out.println("🔧 Spring injected MessageUtility into Controller");
    }
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        // Get message from utility
        String message = messageUtility.getMessage();
        
        // Print object ID to console to verify singleton
        System.out.println("📊 Controller using MessageUtility with Object ID: " + messageUtility.hashCode());
        
        // Create ModelAndView
        ModelAndView mav = new ModelAndView("message");
        mav.addObject("message", message);
        mav.addObject("objectId", messageUtility.hashCode());
        
        return mav;
    }
}