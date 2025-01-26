package com.example.shoppingcart.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shoppingcart.cart.model.entity.Customer;
import com.example.shoppingcart.cart.repository.CustomerRepository;
import com.example.shoppingcart.cart.service.impl.CustomerServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample customer for testing
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setAddress("123 Street");
        customer.setPostalCode("12345");
        customer.setStatus("Active");
        customer.setGender("Male");
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerService.getAllCustomers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerService.getCustomerById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer result = customerService.createCustomer(customer);

        // Assert
        assertNotNull(result);
        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerId(1L);
        updatedCustomer.setFirstName("Jane");
        updatedCustomer.setLastName("Doe");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

        // Act
        Optional<Customer> result = customerService.updateCustomer(1L, updatedCustomer);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Jane", result.get().getFirstName());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(updatedCustomer);
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        // Act
        boolean result = customerService.deleteCustomer(1L);

        // Assert
        assertTrue(result);
        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCustomerNotFound() {
        // Arrange
        when(customerRepository.existsById(1L)).thenReturn(false);

        // Act
        boolean result = customerService.deleteCustomer(1L);

        // Assert
        assertFalse(result);
        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, never()).deleteById(1L);
    }
}
