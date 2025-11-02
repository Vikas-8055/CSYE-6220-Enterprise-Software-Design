package com.spring.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GenerateBookFormController implements Controller {
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        int numBooks = Integer.parseInt(request.getParameter("numBooks"));
        
        ModelAndView mav = new ModelAndView("bookForm");
        mav.addObject("numBooks", numBooks);
        
        return mav;
    }
}