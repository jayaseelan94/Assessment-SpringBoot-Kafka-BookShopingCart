package com.example.shoppingcart.cart.service.api;

import com.example.shoppingcart.cart.model.dto.OrderDto;
import com.example.shoppingcart.cart.model.entity.OrderEntity;

import java.util.List;

public interface OrderBusiness {
	OrderEntity findById(Long id);

    List<OrderEntity> findAll();

    OrderEntity save(OrderEntity orderDto);

    OrderEntity update(OrderEntity orderDto);

    void delete(Long id);
}
