package com.spring.controller;

import com.movie.dao.MovieDAO;
import com.movie.model.Movie;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchMovieController implements Controller {
    
    private MovieDAO movieDAO = new MovieDAO();
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        String keyword = request.getParameter("keyword");
        List<Movie> movies = movieDAO.searchMovies(keyword);
        
        ModelAndView mav = new ModelAndView("movieList");
        mav.addObject("movies", movies);
        mav.addObject("keyword", keyword);
        
        return mav;
    }
}