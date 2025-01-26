package com.example.shoppingcart.payment;

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

import com.example.shoppingcart.payment.model.dto.PaymentDto;
import com.example.shoppingcart.payment.model.entity.PaymentEntity;
import com.example.shoppingcart.payment.repository.PaymentRepository;
import com.example.shoppingcart.payment.service.impl.PaymentBusinessImpl;
import com.example.shoppingcart.payment.service.mapper.PaymentMapper;

public class PaymentBusinessTest {

    @Mock
    private PaymentRepository paymentRepository; // Mock repository

    @InjectMocks
    private PaymentBusinessImpl paymentBusiness; // Service implementation to be tested

    private PaymentEntity paymentEntity;
    private PaymentDto paymentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample payment entity and DTO
        paymentEntity = new PaymentEntity();
        paymentEntity.setId(1L);
        paymentEntity.setOrderId(1001L);
        paymentEntity.setOrderDate(LocalDate.now());
        paymentEntity.setTotalPrice(2000L);

        paymentDto = new PaymentDto();
        paymentDto.setId(1L);
        paymentDto.setOrderId(1001L);
        paymentDto.setOrderDate(LocalDate.now());
        paymentDto.setTotalPrice(2000L);
    }

    @Test
    void testFindById() {
        // Arrange
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(paymentEntity));
        when(PaymentMapper.MAPPER.toDto(paymentEntity));
        
        // Act
        PaymentDto result = paymentBusiness.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(paymentDto.getId(), result.getId());
        assertEquals(paymentDto.getOrderId(), result.getOrderId());
        verify(paymentRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<PaymentEntity> paymentEntities = Arrays.asList(paymentEntity);
        when(paymentRepository.findAll()).thenReturn(paymentEntities);
        when(PaymentMapper.MAPPER.toDto(paymentEntity));
        // Act
        List<PaymentDto> result = paymentBusiness.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(paymentDto.getId(), result.get(0).getId());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Arrange
    	when(PaymentMapper.MAPPER.toEntity(paymentDto));
        when(paymentRepository.save(paymentEntity)).thenReturn(paymentEntity);
        when(PaymentMapper.MAPPER.toDto(paymentEntity));
        // Act
        PaymentDto result = paymentBusiness.save(paymentDto);

        // Assert
        assertNotNull(result);
        assertEquals(paymentDto.getId(), result.getId());
        assertEquals(paymentDto.getOrderId(), result.getOrderId());
        verify(paymentRepository, times(1)).save(paymentEntity);
    }

    @Test
    void testUpdate() {
        // Arrange
        when(paymentRepository.findById(paymentDto.getId())).thenReturn(Optional.of(paymentEntity));
        when(paymentRepository.save(paymentEntity)).thenReturn(paymentEntity);
        when(PaymentMapper.MAPPER.toDto(paymentEntity));
        // Act
        PaymentDto result = paymentBusiness.update(paymentDto);

        // Assert
        assertNotNull(result);
        assertEquals(paymentDto.getId(), result.getId());
        verify(paymentRepository, times(1)).findById(paymentDto.getId());
        verify(paymentRepository, times(1)).save(paymentEntity);
    }

    @Test
    void testProcessPayment() {
        // Arrange
        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(paymentEntity);

        // Act
        boolean result = paymentBusiness.processPayment(1001L, 2000L);

        // Assert
        assertTrue(result);
        verify(paymentRepository, times(1)).save(any(PaymentEntity.class));
    }

    @Test
    void testDelete() {
        // Arrange
        doNothing().when(paymentRepository).deleteById(1L);

        // Act
        paymentBusiness.delete(1L);

        // Assert
        verify(paymentRepository, times(1)).deleteById(1L);
    }
}
