package com.example.shoppingcart.cart.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingcart.cart.model.entity.Customer;
import com.example.shoppingcart.cart.service.api.CustomerService;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
    private CustomerService customerService;
	
	 private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    // Get all customers
	@Operation(summary = "${restfulApi.customer.findAll.value}", description = "${restfulApi.customer.findAll.value}")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
		logger.info("Entering CustomerController getAllCustomers:");
		try {
			List<Customer> customers = customerService.getAllCustomers();
	        return new ResponseEntity<>(customers, HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerController getAllCustomers:"+e.getMessage());
	   	}
        return null;
    }

    @Operation(summary = "${restfulApi.customer.findById.value}", description = "${restfulApi.customer.findById.notes}")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    	logger.info("Entering CustomerController getCustomerById:"+id);
    	try {
    		Optional<Customer> customer =customerService.getCustomerById(id);
	        return customer.map(ResponseEntity::ok)
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerController getCustomerById:"+e.getMessage());
	   	}
        return null;
    }

    // Create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    	logger.info("Entering CustomerController createCustomer");
    	try {
    		Customer savedCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerController createCustomer:"+e.getMessage());
	   	}
        return null;
    }

    // Update an existing customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    	logger.info("Entering CustomerController updateCustomer"+id);
    	try {
    		Optional<Customer> updatedCustomer = customerService.updateCustomer(id, customer);
	        return updatedCustomer.map(ResponseEntity::ok)
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerController updateCustomer:"+e.getMessage());
	   	}
        return null;
    }

    // Delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    	logger.info("Entering CustomerController deleteCustomer"+id);
    	try {
    		boolean isDeleted = customerService.deleteCustomer(id);
	        if (isDeleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerController deleteCustomer:"+e.getMessage());
	   	}
        return null;
    }

}
