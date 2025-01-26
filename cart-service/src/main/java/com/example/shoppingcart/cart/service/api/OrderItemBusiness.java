package com.example.shoppingcart.cart.service.api;

import com.example.shoppingcart.cart.model.dto.OrderItemDto;
import com.example.shoppingcart.cart.model.entity.OrderItemEntity;

import java.util.List;

public interface OrderItemBusiness {
    OrderItemEntity findById(Long id);

    List<OrderItemEntity> findAll();

    OrderItemEntity save(OrderItemEntity orderItemDto);

    OrderItemEntity update(OrderItemEntity orderItemDto);

    boolean delete(Long id);
}
