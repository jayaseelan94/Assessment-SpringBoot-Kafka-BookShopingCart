package com.example.shoppingcart.cart.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingcart.cart.model.entity.Book;
import com.example.shoppingcart.cart.service.api.BookService;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;





@RestController
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookService bookSerivce; 
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    
    @Operation(summary = "${restfulApi.book.findAll.value}", description = "${restfulApi.book.findAll.value}")
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
    	logger.info("Entering BookController getAllBooks:");
    	try {
    		List<Book> books = bookSerivce.getAllBook();
            
            return new ResponseEntity<>(books,HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in BookController getAllBooks:"+e.getMessage());
	   	}
        return null;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
    	logger.info("Entering BookController createBook:");
    	try {
    		 Book savedbook = bookSerivce.createBook(book);
    	     return new ResponseEntity<>(savedbook, HttpStatus.CREATED);
	   	}catch(Exception e) {
	   		logger.error("Error in BookController createBook:"+e.getMessage());
	   	}
        return null;
    }
    
    @Operation(summary = "${restfulApi.book.findById.value}", description = "${restfulApi.book.findById.value}")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
    	logger.info("Entering BookController getBookById:"+id);
    	try {
    		Book existBook = bookSerivce.getBookById(id);
	        if(existBook !=null){
	            return new ResponseEntity<>(existBook,HttpStatus.OK);
	        } else{
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	   	}catch(Exception e) {
	   		logger.error("Error in BookController getBookById:"+e.getMessage());
	   	}
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
    	logger.info("Entering BookController updateBook:"+id);
    	try {
    		Optional<Book> updatedbook = bookSerivce.updateBook(id, book);
    		updatedbook.map(ResponseEntity::ok)
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	   	}catch(Exception e) {
	   		logger.error("Error in BookController updateBook:"+e.getMessage());
	   	}
        return null;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    	logger.info("Entering BookController deleteBook"+id);
    	try {
    		boolean isDeleted = bookSerivce.deleteBook(id);
	        if (isDeleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	   	}catch(Exception e) {
	   		logger.error("Error in BookController deleteBook:"+e.getMessage());
	   	}
        return null;
    }
}
