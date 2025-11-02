package com.spring.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MovieHomeController implements Controller {
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        return new ModelAndView("movieHome");
    }
}