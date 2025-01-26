package com.example.shoppingcart.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shoppingcart.cart.model.entity.OrderItemEntity;
import com.example.shoppingcart.cart.repository.OrderItemRepository;
import com.example.shoppingcart.cart.service.impl.OrderItemBusinessImpl;

public class OrderItemBusinessTest {

    @Mock
    private OrderItemRepository orderItemRepository; // Mock repository

    @InjectMocks
    private OrderItemBusinessImpl orderItemBusiness; // Service to be tested

    private OrderItemEntity orderItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample order item for testing
        orderItem = new OrderItemEntity();
        orderItem.setId(1L);
        orderItem.setBookId(1001L);
        orderItem.setQuantity(2L);
        orderItem.setBookPrice(250L);
    }

    @Test
    void testFindById() {
        // Arrange
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));

        // Act
        OrderItemEntity result = orderItemBusiness.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(orderItem.getId(), result.getId());
        assertEquals(orderItem.getBookId(), result.getBookId());
        assertEquals(orderItem.getQuantity(), result.getQuantity());
        verify(orderItemRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        OrderItemEntity result = orderItemBusiness.findById(1L);

        // Assert
        assertNull(result);
        verify(orderItemRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<OrderItemEntity> orderItems = Arrays.asList(orderItem);
        when(orderItemRepository.findAll()).thenReturn(orderItems);

        // Act
        List<OrderItemEntity> result = orderItemBusiness.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(orderItem, result.get(0));
        verify(orderItemRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Arrange
        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);

        // Act
        OrderItemEntity result = orderItemBusiness.save(orderItem);

        // Assert
        assertNotNull(result);
        assertEquals(orderItem.getId(), result.getId());
        assertEquals(orderItem.getBookId(), result.getBookId());
        verify(orderItemRepository, times(1)).save(orderItem);
    }

    @Test
    void testUpdate() {
        // Arrange
        OrderItemEntity updatedOrderItem = new OrderItemEntity();
        updatedOrderItem.setId(1L);
        updatedOrderItem.setBookId(1002L);
        updatedOrderItem.setQuantity(3L);
        updatedOrderItem.setBookPrice(300L);

        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));
        when(orderItemRepository.save(updatedOrderItem)).thenReturn(updatedOrderItem);

        // Act
        OrderItemEntity result = orderItemBusiness.update(updatedOrderItem);

        // Assert
        assertNotNull(result);
        assertEquals(updatedOrderItem.getId(), result.getId());
        assertEquals(updatedOrderItem.getBookId(), result.getBookId());
        assertEquals(updatedOrderItem.getQuantity(), result.getQuantity());
        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(1)).save(updatedOrderItem);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        when(orderItemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        OrderItemEntity result = orderItemBusiness.update(orderItem);

        // Assert
        assertNull(result);
        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, never()).save(any(OrderItemEntity.class));
    }

    @Test
    void testDelete() {
        // Arrange
        when(orderItemRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderItemRepository).deleteById(1L);

        // Act
        boolean result = orderItemBusiness.delete(1L);

        // Assert
        assertTrue(result);
        verify(orderItemRepository, times(1)).existsById(1L);
        verify(orderItemRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(orderItemRepository.existsById(1L)).thenReturn(false);

        // Act
        boolean result = orderItemBusiness.delete(1L);

        // Assert
        assertFalse(result);
        verify(orderItemRepository, times(1)).existsById(1L);
        verify(orderItemRepository, never()).deleteById(1L);
    }
}
