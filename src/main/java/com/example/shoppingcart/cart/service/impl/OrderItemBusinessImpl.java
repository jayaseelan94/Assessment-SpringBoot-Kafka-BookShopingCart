package com.example.shoppingcart.cart.service.impl;


import com.example.shoppingcart.cart.exception.EntityNotFoundException;
import com.example.shoppingcart.cart.model.entity.OrderItemEntity;
import com.example.shoppingcart.cart.repository.OrderItemRepository;
import com.example.shoppingcart.cart.service.api.OrderItemBusiness;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("OrderItemBusinessImpl")
@CacheConfig(cacheNames = "cart")
public class OrderItemBusinessImpl implements OrderItemBusiness {

    @Autowired
    OrderItemRepository orderItemRepository;
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public OrderItemEntity findById(Long id) {
    	logger.info("Entering OrderBusinessService findById");
    	try {
    		return orderItemRepository.findById(id).
                    orElseThrow(() -> new EntityNotFoundException("error.order.not.found.Exception", id));
	   	}catch(Exception e) {
	   		logger.error("Error in OrderBusinessService findById:"+e.getMessage());
	   	}
        return null;
    }

    @Override
    public List<OrderItemEntity> findAll() {
    	logger.info("Entering OrderBusinessService findAll");
    	try {
    		return orderItemRepository.findAll();
	   	}catch(Exception e) {
	   		logger.error("Error in OrderBusinessService findAll:"+e.getMessage());
	   	}
        return null;
    }

    @Override
    public OrderItemEntity save(OrderItemEntity orderItemEntity) {
    	logger.info("Entering OrderBusinessService save");
    	try {
    		return orderItemRepository.save(orderItemEntity);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderBusinessService save:"+e.getMessage());
	   	}
        return null;
    }

    @Override
    public OrderItemEntity update(OrderItemEntity orderItemEntity) {
    	logger.info("Entering OrderBusinessService update");
    	try {
    		return orderItemRepository.save(orderItemEntity);
	   	}catch(Exception e) {
	   		logger.error("Error in OrderBusinessService update:"+e.getMessage());
	   	}
    	return null;
    }

    @Override
    @CacheEvict(value = "cart", key = "#id")
    public boolean delete(Long id) {
    	logger.info("Entering OrderBusinessService delete");
    	try {
    		 if(orderItemRepository.existsById(id)) {
    			 orderItemRepository.deleteById(id);
 	            return true;
 	        }
 		 return false;
	   	}catch(Exception e) {
	   		logger.error("Error in OrderBusinessService delete:"+e.getMessage());
	   	}
    	return false;
    }
}
