package com.movie.controller;

import com.movie.dao.MovieDAO;
import com.movie.model.Movie;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/movieController")
public class MovieController extends HttpServlet {
    private MovieDAO movieDAO;

    @Override
    public void init() {
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "home";
        }
        
        switch (action) {
            case "browse":
                browseMovies(request, response);
                break;
            case "addForm":
                showAddForm(request, response);
                break;
            case "searchForm":
                showSearchForm(request, response);
                break;
            case "search":
                searchMovies(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteMovie(request, response);
                break;
            default:
                response.sendRedirect("movieHome.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        switch (action) {
            case "add":
                addMovie(request, response);
                break;
            case "update":
                updateMovie(request, response);
                break;
            default:
                response.sendRedirect("movieHome.jsp");
        }
    }

    private void browseMovies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Movie> movies = movieDAO.getAllMovies();
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("movieList.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addMovie.jsp").forward(request, response);
    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("searchMovie.jsp").forward(request, response);
    }

    private void searchMovies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Movie> movies = movieDAO.searchMovies(keyword);
        request.setAttribute("movies", movies);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("movieList.jsp").forward(request, response);
    }

    private void addMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        
        movieDAO.addMovie(movie);
        response.sendRedirect("movieController?action=browse");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Movie movie = movieDAO.getMovieById(id);
        request.setAttribute("movie", movie);
        request.getRequestDispatcher("editMovie.jsp").forward(request, response);
    }

    private void updateMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        response.sendRedirect("movieController?action=browse");
    }

    private void deleteMovie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        movieDAO.deleteMovie(id);
        response.sendRedirect("movieController?action=browse");
    }
}