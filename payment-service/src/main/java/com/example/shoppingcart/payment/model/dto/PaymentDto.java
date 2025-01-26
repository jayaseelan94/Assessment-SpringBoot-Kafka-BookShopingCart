package com.example.shoppingcart.payment.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PaymentDto implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long id;

    private Long orderId;

    private LocalDate orderDate;

    private Long totalPrice;
}
