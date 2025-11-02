package com.spring.controller;

import com.spring.util.MessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MessageControllerRequest implements Controller {
    
    // Part 9: Using @Autowired with REQUEST scope bean
    @Autowired
    @Qualifier("messageUtilityRequest")
    private MessageUtility messageUtility;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        String message = messageUtility.getMessage();
        
        // Print object ID - should be DIFFERENT each request!
        System.out.println("🔄 Part 9 (Request Scope) - MessageUtility Object ID: " + messageUtility.hashCode());
        
        ModelAndView mav = new ModelAndView("messageRequest");
        mav.addObject("message", message);
        mav.addObject("objectId", messageUtility.hashCode());
        
        return mav;
    }
}