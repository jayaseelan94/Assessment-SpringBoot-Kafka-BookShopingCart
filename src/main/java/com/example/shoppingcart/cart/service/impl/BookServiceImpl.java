package com.example.shoppingcart.cart.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.example.shoppingcart.cart.model.entity.Book;
import com.example.shoppingcart.cart.model.entity.Customer;
import com.example.shoppingcart.cart.repository.BookRepository;
import com.example.shoppingcart.cart.service.api.BookService;

import ch.qos.logback.classic.Logger;


@Service
@CacheConfig(cacheNames = "cart")
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookRepository bookRepository;

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Book> getAllBook(){
    	logger.info("Entering BookService getAllBooks:");
    	try {
    		return bookRepository.findAll();
    	}catch(Exception e) {
    		logger.error("Error in BookServiceImpl getAllBook:"+e.getMessage());
    	}
    	return null;
    }
    
    @Override
    public Book createBook(Book book) {
    	logger.info("Entering BookService createBook:");
    	try {
    		return bookRepository.save(book);
    	}catch(Exception e) {
    		logger.error("Error in BookServiceImpl createBook:"+e.getMessage());
    	}
        return null;
    }


    @Override
    public Book getBookById(Long id){
    	logger.info("Entering BookService getBookById:"+id);
        try {
        	return bookRepository.findById(id).orElse(null);
    	}catch(Exception e) {
    		logger.error("Error in BookServiceImpl getBookById:"+e.getMessage());
    	}
        return null;
    }

    
    @Override
    public List<Book> searchBooks(String query) {
    	logger.info("Entering BookService searchBooks:");
    	try {
    		return bookRepository.searchBooks(query);
    	}catch(Exception e) {
    		logger.error("Error in BookServiceImpl searchBooks:"+e.getMessage());
    	}
        return null;
    }
       
    @Override
    public Optional<Book> updateBook(Long id, Book book){
    	logger.info("Entering BookService updateBook:");
    	try {
    		 return bookRepository.findById(id).map(existingBook -> {
    			 existingBook.setBookAuthor(book.getBookAuthor());
    			 existingBook.setBookTitle(book.getBookTitle());
    			 existingBook.setBookPrice(book.getBookPrice());
    			 existingBook.setDescription(book.getDescription());	 
 	            return bookRepository.save(existingBook);
 	        });
    	}catch(Exception e) {
    		logger.error("Error in BookServiceImpl updateBook:"+e.getMessage());
    	}
        return null;
    }
    
    @Override
    public boolean deleteBook(Long id) {
    	logger.info("Entering BookServiceImpl deleteBook:");
    	try {
		 if (bookRepository.existsById(id)) {
			 bookRepository.deleteById(id);
	            return true;
	        }
		 return false;
	   	}catch(Exception e) {
	   		logger.error("Error in BookServiceImpl deleteBook:"+e.getMessage());
	   	}
    	return false;
    }
}
