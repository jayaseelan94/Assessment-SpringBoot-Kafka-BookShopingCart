package com.example.shoppingcart.cart.controller;

import com.example.shoppingcart.cart.model.dto.OrderDto;
import com.example.shoppingcart.cart.model.entity.Customer;
import com.example.shoppingcart.cart.model.entity.OrderEntity;
import com.example.shoppingcart.cart.service.api.OrderBusiness;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderBusiness orderBusiness;
    
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Operation(summary = "${restfulApi.order.findById.value}", description = "${restfulApi.order.findById.value}")
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> findById(@PathVariable Long id) {
    	logger.info("Entering OrderController findById:"+id);
    	try {
    		Optional<OrderEntity> orderEntity = Optional.of(orderBusiness.findById(id));
    		return orderEntity.map(ResponseEntity::ok)
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	   	}catch(Exception e) {
	   		logger.error("Error in CustomerController getAllCustomers:"+e.getMessage());
	   	}
        return null;
    }

    @Operation(summary = "${restfulApi.order.findAll.value}", description = "${restfulApi.order.findAll.value}")
    @GetMapping("/find-all")
    public ResponseEntity<List<OrderEntity>> findAll() {
    	logger.info("Entering OrderController findAll");
    	List<OrderEntity> orderList = new ArrayList<>();
    	try {
    		orderList =  orderBusiness.findAll();
 	        return new ResponseEntity<>(orderList, HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderController findAll:"+e.getMessage());
	   	}
        return null;
       
    }

    @PostMapping("/create")
    public ResponseEntity<OrderEntity> create(@RequestBody OrderEntity orderDto) {
    	logger.info("Entering OrderController create");
    	try {
    		OrderEntity orderEntity = orderBusiness.save(orderDto);
    		return new ResponseEntity<>(orderEntity, HttpStatus.CREATED);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderController create:"+e.getMessage());
	   	}
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<OrderEntity> update(@RequestBody OrderEntity orderDto) {
    	logger.info("Entering OrderController update");
    	try {
    		OrderEntity orderEntity = orderBusiness.update(orderDto);
    		return new ResponseEntity<>(orderEntity, HttpStatus.CREATED);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderController update:"+e.getMessage());
	   	}
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //    @ApiOperation(value = "${restfulApi.order.delete.value}", notes = "${restfulApi.order.delete.value}")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	logger.info("Entering OrderController delete:"+id);
    	try {
    		 orderBusiness.delete(id);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderController delete:"+e.getMessage());
	   	}
    }
}
