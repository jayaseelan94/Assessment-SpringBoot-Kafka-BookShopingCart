package com.example.shoppingcart.cart.model.dto;

import com.example.shoppingcart.cart.model.enums.OrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;

    private Long orderId;

    private OrderStatus status;

    private LocalDate orderDate;

    private Long totalPrice;

//    private List<OrderItemDto> orderItemDtoList;
}
