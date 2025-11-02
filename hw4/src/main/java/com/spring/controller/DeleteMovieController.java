package com.spring.controller;

import com.movie.dao.MovieDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteMovieController implements Controller {
    
    private MovieDAO movieDAO = new MovieDAO();
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        int id = Integer.parseInt(request.getParameter("id"));
        movieDAO.deleteMovie(id);
        
        return new ModelAndView("redirect:/browse");
    }
}