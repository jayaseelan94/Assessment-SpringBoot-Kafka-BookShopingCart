package com.example.shoppingcart.cart.service.api;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shoppingcart.cart.model.entity.Customer;



@Service
public interface CustomerService {
	List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Optional<Customer> updateCustomer(Long id, Customer customer);
    boolean deleteCustomer(Long id);
    
}
