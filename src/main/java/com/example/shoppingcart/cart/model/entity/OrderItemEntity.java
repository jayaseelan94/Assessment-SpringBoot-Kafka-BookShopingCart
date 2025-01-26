package com.example.shoppingcart.cart.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOKـID")
    private Long bookId;

    @Column(name = "QUANTITY")
    private Long quantity;
    
    @Column(name = "BOOK_PRICE")
    private Long bookPrice;
    
    @Transient
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;  // Ensure this field is properly set up as a reference to Customer


}
