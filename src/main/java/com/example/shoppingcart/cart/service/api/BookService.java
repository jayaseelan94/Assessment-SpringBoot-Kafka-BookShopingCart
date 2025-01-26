package com.example.shoppingcart.cart.service.api;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shoppingcart.cart.model.entity.Book;
import com.example.shoppingcart.cart.model.entity.Customer;



@Service
public interface BookService {
    List<Book> getAllBook();
    Book getBookById(Long id);
    Book createBook(Book book);
    List<Book> searchBooks(String query);
    Optional<Book>  updateBook(Long id, Book book);
	boolean deleteBook(Long id);
    
}
