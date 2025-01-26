package com.example.shoppingcart.cart.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column
    private String bookTitle;

    @Column
    private String description;

    @Column
    private double bookPrice;

    @Column
    private String bookAuthor;
    
    public Book() {}
    
    public Book(Long id, String title, String author,double bookPrice, String description) {
        this.id = id;
        this.bookTitle = title;
        this.bookAuthor = author;
        this.bookPrice = bookPrice;
        this.description = description;
    }
}
