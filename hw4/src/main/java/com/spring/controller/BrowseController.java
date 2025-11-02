package com.spring.controller;

import com.movie.dao.MovieDAO;
import com.movie.model.Movie;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class BrowseController implements Controller {
    
    private MovieDAO movieDAO = new MovieDAO();
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        List<Movie> movies = movieDAO.getAllMovies();
        
        ModelAndView mav = new ModelAndView("movieList");
        mav.addObject("movies", movies);
        
        return mav;
    }
}