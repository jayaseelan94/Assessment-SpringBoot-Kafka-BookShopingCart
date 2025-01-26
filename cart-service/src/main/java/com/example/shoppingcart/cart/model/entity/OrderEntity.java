package com.example.shoppingcart.cart.model.entity;

import com.example.shoppingcart.cart.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_ITEM_ID")
    private Long orderItemId;

    @Column(name = "ORDER_STATUS")
    private String status;

    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;
    
    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;

  
}
