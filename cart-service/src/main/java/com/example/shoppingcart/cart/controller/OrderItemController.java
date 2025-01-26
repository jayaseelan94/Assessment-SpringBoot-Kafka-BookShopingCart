package com.example.shoppingcart.cart.controller;

import com.example.shoppingcart.cart.model.entity.Book;
import com.example.shoppingcart.cart.model.entity.Customer;
import com.example.shoppingcart.cart.model.entity.OrderEntity;
import com.example.shoppingcart.cart.model.entity.OrderItemEntity;
import com.example.shoppingcart.cart.service.api.OrderItemBusiness;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemBusiness orderItemBusiness;
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


    @Operation(summary = "${restfulApi.orderItem.findById.value}", description = "${restfulApi.orderItem.findById.notes}")
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemEntity> findById(@PathVariable Long id) {
    	logger.info("Entering OrderItemController findById:"+id);
    	try {
    		Optional<OrderItemEntity> orderitemEntity = Optional.of(orderItemBusiness.findById(id));
    		 return orderitemEntity.map(ResponseEntity::ok)
 	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	   	}catch(Exception e) {
	   		logger.error("Error in OrderItemController findById:"+e.getMessage());
	   	}
        return null;
    }

    @Operation(summary = "${restfulApi.orderItem.findAll.value}", description = "${restfulApi.orderItem.findAll.value}")
    @GetMapping("/find-all")
    public ResponseEntity<List<OrderItemEntity>> findAll() {
    	logger.info("Entering OrderItemController findAll:");
        try {
    		List<OrderItemEntity> orderItemList = orderItemBusiness.findAll();
            
            return new ResponseEntity<>(orderItemList,HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderItemController findAll:"+e.getMessage());
	   	}
        return null;
    }

    //    @ApiOperation(value = "${restfulApi.orderItem.save.value}", notes = "${restfulApi.orderItem.save.value}")
    @PostMapping("/create")
    public ResponseEntity<OrderItemEntity> create(@RequestBody OrderItemEntity orderItemDto) {
    	logger.info("Entering OrderBusinessService create:");
        try {
        	OrderItemEntity orderItemEntity = orderItemBusiness.save(orderItemDto);
            return new ResponseEntity<>(orderItemEntity, HttpStatus.CREATED);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderBusinessService create:"+e.getMessage());
	   	}
        return null;
    }

    //    @ApiOperation(value = "${restfulApi.orderItem.update.value}", notes = "${restfulApi.orderItem.update.value}")
    @PutMapping("/update")
    public ResponseEntity<OrderItemEntity>  update(@RequestBody OrderItemEntity orderItemDto) {
    	logger.info("Entering OrderItemController update:");
        try {
    		OrderItemEntity orderItemEntity =  orderItemBusiness.update(orderItemDto);
    		 return new ResponseEntity<>(orderItemEntity, HttpStatus.OK);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderItemController update:"+e.getMessage());
	   	}
        return null;
    }

    //    @ApiOperation(value = "${restfulApi.orderItem.delete.value}", notes = "${restfulApi.orderItem.delete.value}") 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	logger.info("Entering OrderItemController delete"+id);
    	try {
    		boolean isDeleted = orderItemBusiness.delete(id);
	        if (isDeleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	   	}catch(Exception e) {
	   		logger.error("Error in OrderItemController delete:"+e.getMessage());
	   	}
        return null;
    }
}
