package com.example.shoppingcart.cart.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.example.shoppingcart.cart.repository.CustomerRepository;
import com.example.shoppingcart.cart.service.api.CustomerService;

import ch.qos.logback.classic.Logger;

import com.example.shoppingcart.cart.model.entity.Customer;

@Service
@CacheConfig(cacheNames = "cart")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
    private CustomerRepository customerRepository;
	
	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Customer> getAllCustomers() {
    	logger.info("Entering CustomerServiceImpl getAllCustomers");
    	try {
    		return customerRepository.findAll();
    	}catch(Exception e) {
    		logger.error("Error in CustomerServiceImpl getAllCustomers:"+e.getMessage());
    	}
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
    	logger.info("Entering CustomerServiceImpl getCustomerById:"+id);
    	try {
    		 return customerRepository.findById(id);
    	}catch(Exception e) {
    		logger.error("Error in CustomerServiceImpl getCustomerById:"+e.getMessage());
    	}
        return null;
       
    }

    @Override
    public Customer createCustomer(Customer customer) {
    	logger.info("Entering CustomerServiceImpl createCustomer:");
    	try {
    		return customerRepository.save(customer);
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerServiceImpl createCustomer:"+e.getMessage());
	   	}
        return null;
        
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer customer) {
    	logger.info("Entering CustomerServiceImpl updateCustomer:");
    	try {
	        return customerRepository.findById(id).map(existingCustomer -> {
	            existingCustomer.setFirstName(customer.getFirstName());
	            existingCustomer.setLastName(customer.getLastName());
	            existingCustomer.setEmail(customer.getEmail());
	            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
	            existingCustomer.setAddress(customer.getAddress());
	            existingCustomer.setPostalCode(customer.getPostalCode());
	            existingCustomer.setStatus(customer.getStatus());
	            existingCustomer.setGender(customer.getGender());
	            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
	            return customerRepository.save(existingCustomer);
	        });
    	}catch(Exception e) {
	   		logger.error("Error in CustomerServiceImpl updateCustomer:"+e.getMessage());
	   	}
        return null;
    }

    @Override
    public boolean deleteCustomer(Long id) {
    	logger.info("Entering CustomerServiceImpl deleteCustomer:");
    	try {
		 if (customerRepository.existsById(id)) {
	            customerRepository.deleteById(id);
	            return true;
	        }
		 return false;
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerServiceImpl deleteCustomer:"+e.getMessage());
	   	}
    	return false;
    }
}
