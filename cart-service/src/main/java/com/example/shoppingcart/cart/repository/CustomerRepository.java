package com.example.shoppingcart.cart.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.example.shoppingcart.cart.model.entity.Customer;


@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    
}
