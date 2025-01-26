package com.example.shoppingcart.payment.service.impl;

import com.example.shoppingcart.payment.exception.EntityNotFoundException;
import com.example.shoppingcart.payment.model.dto.PaymentDto;
import com.example.shoppingcart.payment.model.entity.PaymentEntity;
import com.example.shoppingcart.payment.repository.PaymentRepository;
import com.example.shoppingcart.payment.service.api.PaymentBusiness;
import com.example.shoppingcart.payment.service.mapper.PaymentMapper;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentBusinessImpl implements PaymentBusiness {

    @Autowired
    PaymentRepository paymentRepository;
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public PaymentDto findById(Long id) {
		logger.info("Entering PaymentBusinessImpl findById:");
		try {
			PaymentEntity paymentEntity = paymentRepository.findById(id).
	                orElseThrow(() -> new EntityNotFoundException("error.payment.not.found.Exception", id));
	        return PaymentMapper.MAPPER.toDto(paymentEntity);
		}catch(Exception e) {
			logger.error("Error in PaymentBusinessImpl findById:"+e.getMessage());
		}
		return null;
    }

    @Override
    public List<PaymentDto> findAll() {
    	logger.info("Entering PaymentBusinessImpl findAll:");
		try {
			List<PaymentEntity> paymentEntityList = paymentRepository.findAll();
	        return paymentEntityList.stream().map(PaymentMapper.MAPPER::toDto).collect(Collectors.toList());
		}catch(Exception e) {
			logger.error("Error in BookServiceImpl findAll:"+e.getMessage());
		}
		return null;
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
    	logger.info("Entering PaymentBusinessImpl save:");
		try {
			PaymentEntity paymentEntity = PaymentMapper.MAPPER.toEntity(paymentDto);
	        PaymentEntity order = paymentRepository.save(paymentEntity);
	        return PaymentMapper.MAPPER.toDto(order);
		}catch(Exception e) {
			logger.error("Error in BookServiceImpl save:"+e.getMessage());
		}
		return null;
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
    	logger.info("Entering PaymentBusinessImpl update:");
		try {
			PaymentEntity paymentEntity = PaymentMapper.MAPPER.toEntity(paymentDto);
	        paymentEntity = paymentRepository.save(paymentEntity);
	        return PaymentMapper.MAPPER.toDto(paymentEntity);
		}catch(Exception e) {
			logger.error("Error in BookServiceImpl update:"+e.getMessage());
		}
		return null;
    }

    @Override
    public boolean processPayment(Long orderId, Long totalPrice) {
    	logger.info("Entering PaymentBusinessImpl processPayment:");
		try {
			 PaymentDto paymentDto = new PaymentDto();
		        paymentDto.setOrderId(orderId);
		        paymentDto.setOrderDate(LocalDate.now());
		        paymentDto.setTotalPrice(totalPrice);
		        save(paymentDto);
	        return true;
		}catch(Exception e) {
			logger.error("Error in BookServiceImpl processPayment:"+e.getMessage());
		}
		return false;
    }

    @Override
    public void delete(Long id) {
    	logger.info("Entering PaymentBusinessImpl delete:");
		try {
			paymentRepository.deleteById(id);
		}catch(Exception e) {
			logger.error("Error in BookServiceImpl delete:"+e.getMessage());
		}
    }
}
