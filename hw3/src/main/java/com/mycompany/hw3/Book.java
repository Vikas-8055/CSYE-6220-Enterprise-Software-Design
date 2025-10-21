package com.mycompany.hw3;

import java.io.Serializable;

/**
 * Book bean representing a book in the database
 */
public class Book implements Serializable {
    private String isbn;
    private String title;
    private String authors;
    private float price;
    
    // Default constructor
    public Book() {
    }
    
    // Constructor with all fields
    public Book(String isbn, String title, String authors, float price) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.price = price;
    }
    
    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthors() {
        return authors;
    }
    
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", price=" + price +
                '}';
    }
}