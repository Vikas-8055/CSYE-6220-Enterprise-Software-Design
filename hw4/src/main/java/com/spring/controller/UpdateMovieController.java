package com.spring.controller;

import com.movie.dao.MovieDAO;
import com.movie.model.Movie;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateMovieController implements Controller {
    
    private MovieDAO movieDAO = new MovieDAO();
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        
        movieDAO.updateMovie(movie);
        
        return new ModelAndView("redirect:/browse");
    }
}