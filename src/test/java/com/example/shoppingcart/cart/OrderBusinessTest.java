package com.example.shoppingcart.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shoppingcart.cart.model.entity.OrderEntity;
import com.example.shoppingcart.cart.repository.OrderRepository;
import com.example.shoppingcart.cart.service.impl.OrderBusinessImpl;

public class OrderBusinessTest {

    @Mock
    private OrderRepository orderRepository; // Mock repository

    @InjectMocks
    private OrderBusinessImpl orderBusiness; // Service to be tested

    private OrderEntity order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample order for testing
        order = new OrderEntity();
        order.setId(1L);
        order.setOrderItemId(1001L);
        order.setStatus("Confirmed");
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(200L);
    }

    @Test
    void testFindById() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        OrderEntity result = orderBusiness.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getStatus(), result.getStatus());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        OrderEntity result = orderBusiness.findById(1L);

        // Assert
        assertNull(result);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<OrderEntity> orders = Arrays.asList(order);
        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        List<OrderEntity> result = orderBusiness.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(order, result.get(0));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Arrange
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        OrderEntity result = orderBusiness.save(order);

        // Assert
        assertNotNull(result);
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getStatus(), result.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testUpdate() {
        // Arrange
        OrderEntity updatedOrder = new OrderEntity();
        updatedOrder.setId(1L);
        updatedOrder.setOrderItemId(1002L);
        updatedOrder.setStatus("Shipped");
        updatedOrder.setOrderDate(order.getOrderDate());
        updatedOrder.setTotalPrice(300L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        // Act
        OrderEntity result = orderBusiness.update(updatedOrder);

        // Assert
        assertNotNull(result);
        assertEquals(updatedOrder.getId(), result.getId());
        assertEquals("Shipped", result.getStatus());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(updatedOrder);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        OrderEntity result = orderBusiness.update(order);

        // Assert
        assertNull(result);
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, never()).save(any(OrderEntity.class));
    }

    @Test
    void testDelete() {
        // Arrange
        when(orderRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(1L);

        // Act
        orderBusiness.delete(1L);

        // Assert
        verify(orderRepository, times(1)).existsById(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(orderRepository.existsById(1L)).thenReturn(false);

        // Act
        orderBusiness.delete(1L);

        // Assert
        verify(orderRepository, times(1)).existsById(1L);
        verify(orderRepository, never()).deleteById(1L);
    }
}