package com.example.shoppingcart.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.shoppingcart.cart.model.entity.Book;


@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b " +
       "WHERE (:query is null OR " +
       "b.bookTitle LIKE %:query% OR " +
       "b.bookAuthor LIKE %:query%)")
    List<Book> searchBooks(@Param("query") String query);



    

}
