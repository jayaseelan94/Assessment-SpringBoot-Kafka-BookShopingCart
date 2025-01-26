package com.example.shoppingcart.payment.controller;

import com.example.shoppingcart.payment.model.dto.PaymentDto;
import com.example.shoppingcart.payment.service.api.PaymentBusiness;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentBusiness paymentBusiness;
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Operation(summary = "${restfulApi.payment.findById.value}", description = "${restfulApi.payment.findById.notes}")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable Long id) {
    	logger.info("Entering PaymentController findById:");
    	try {
    		PaymentDto PaymentDto = paymentBusiness.findById(id);
            
            return new ResponseEntity<>(PaymentDto,HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in PaymentController findById:"+e.getMessage());
	   	}
        return null;
    }

    @Operation(summary = "${restfulApi.payment.findAll.value}", description = "${restfulApi.payment.findAll.value}")
    @GetMapping("/find-all")
    public ResponseEntity<List<PaymentDto>> findAll() {
    	logger.info("Entering PaymentController findAll:");
    	try {
    		List<PaymentDto> PaymentListDto = paymentBusiness.findAll();
            
            return new ResponseEntity<>(PaymentListDto,HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in PaymentController findAll:"+e.getMessage());
	   	}
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto paymentDto) {
    	logger.info("Entering PaymentController create:");
    	try {
    		PaymentDto PaymentDto =  paymentBusiness.save(paymentDto);
            
            return new ResponseEntity<>(PaymentDto,HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in PaymentController create:"+e.getMessage());
	   	}
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto paymentDto) {
    	logger.info("Entering PaymentController update:");
    	try {
    		PaymentDto payment =  paymentBusiness.update(paymentDto);
            
            return new ResponseEntity<>(payment,HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in PaymentController update:"+e.getMessage());
	   	}
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	logger.info("Entering PaymentController delete:");
    	try {
    		paymentBusiness.delete(id);
            
            return new ResponseEntity<>(HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in PaymentController delete:"+e.getMessage());
	   	}
        return null;
    }
    
    
}
