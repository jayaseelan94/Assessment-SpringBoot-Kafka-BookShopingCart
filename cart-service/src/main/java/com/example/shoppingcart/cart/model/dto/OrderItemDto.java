package com.example.shoppingcart.cart.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;

    private Long bookId;
    
    private Long bookPrice;

    private Long customerId;
    
    private Long quantity;


//    private OrderDto orderDto;
}
