package com.spring.controller;

import com.movie.dao.MovieDAO;
import com.movie.model.Movie;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditMovieController implements Controller {
    
    private MovieDAO movieDAO = new MovieDAO();
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Movie movie = movieDAO.getMovieById(id);
        
        ModelAndView mav = new ModelAndView("editMovie");
        mav.addObject("movie", movie);
        
        return mav;
    }
}