package com.spring.controller;

import com.spring.util.MessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MessageControllerAutowired implements Controller {
    
    @Autowired
    private MessageUtility messageUtility;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        String message = messageUtility.getMessage();
        
        System.out.println("📊 Part 8 (@Autowired) - MessageUtility Object ID: " + messageUtility.hashCode());
        
        ModelAndView mav = new ModelAndView("messageAutowired");
        mav.addObject("message", message);
        mav.addObject("objectId", messageUtility.hashCode());
        
        return mav;
    }
}