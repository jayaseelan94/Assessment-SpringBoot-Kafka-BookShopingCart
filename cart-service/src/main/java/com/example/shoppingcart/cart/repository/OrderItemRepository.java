package com.example.shoppingcart.cart.repository;

import com.example.shoppingcart.cart.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
