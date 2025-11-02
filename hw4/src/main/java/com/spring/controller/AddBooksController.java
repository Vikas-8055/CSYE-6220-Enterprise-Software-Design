package com.spring.controller;

import com.book.dao.BookDAO;
import com.book.model.Book;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddBooksController implements Controller {
    
    private BookDAO bookDAO = new BookDAO();
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, 
                                     HttpServletResponse response) throws Exception {
        
        String numBooksStr = request.getParameter("numBooks");
        int numBooks = Integer.parseInt(numBooksStr);
        
        List<Book> books = new ArrayList<>();
        
        // Get book data from form
        for (int i = 0; i < numBooks; i++) {
            String isbn = request.getParameter("isbn_" + i);
            String title = request.getParameter("title_" + i);
            String authors = request.getParameter("authors_" + i);
            String priceStr = request.getParameter("price_" + i);
            
            if (isbn != null && title != null && authors != null && priceStr != null) {
                float price = Float.parseFloat(priceStr);
                Book book = new Book(isbn, title, authors, price);
                books.add(book);
            }
        }
        
        // Save books using PreparedStatement
        bookDAO.addBooks(books);
        
        ModelAndView mav = new ModelAndView("booksAdded");
        mav.addObject("booksAdded", books.size());
        
        return mav;
    }
}