package com.example.shoppingcart.cart.service.impl;

import com.example.shoppingcart.cart.event.OrderPlacedProducer;
import com.example.shoppingcart.cart.exception.EntityNotFoundException;
import com.example.shoppingcart.cart.model.entity.OrderEntity;
import com.example.shoppingcart.cart.repository.OrderRepository;
import com.example.shoppingcart.cart.service.api.OrderBusiness;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("OrderBusinessImpl")
@CacheConfig(cacheNames = "cart")
public class OrderBusinessImpl implements OrderBusiness {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderPlacedProducer orderPlacedProducer;
    
    @Autowired
    HazelcastInstance hazelcastInstance;
    
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public OrderEntity findById(Long id) {
    	logger.info("Entering OrderBusinessService findByID:"+id);
    	try {
	        OrderEntity orderEntity = orderRepository.findById(id).
	                orElseThrow(() -> new EntityNotFoundException("error.order.not.found.Exception", id));
	        
	        return orderEntity;
    	}catch(Exception e) {
    		logger.error("Error in OrderBusinessService findById:"+e.getMessage());
    	}
    	return null;
    }

    @Override
    public List<OrderEntity> findAll() {
    	logger.info("Entering OrderBusinessService findAll");
    	List<OrderEntity> orderEntityList = new ArrayList<>();
    	try {
	        orderEntityList = orderRepository.findAll();
	        
	        IMap<Long, OrderEntity> OrderEntity = hazelcastInstance.getMap("cart");
	        
	        orderEntityList.forEach( x ->{
	        	OrderEntity.put(x.getId(), x);
	        });
	        
	        return orderEntityList;
    	}catch(Exception e) {
    		logger.error("Error in OrderBusinessService findAll:"+e.getMessage());
    	}
    	return orderEntityList;
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
    	logger.info("Entering OrderBusinessService save");
    	OrderEntity order = new OrderEntity();
    	try {
	        order = orderRepository.save(orderEntity);
	        orderPlacedProducer.sendOrderPlacedEvent(order.getId(),order.getTotalPrice());
	        
	        hazelcastInstance.getMap("cart").put(order.getId(), order);
    	}catch(Exception e) {
    		logger.error("Error in OrderBusinessService save:"+e.getMessage());
    	}
        return order;
    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) {
    	logger.info("Entering OrderBusinessService update");
    	try {
	    	OrderEntity order =  orderRepository.save(orderEntity);
	        
	        IMap<Long, OrderEntity> OrderEntity = hazelcastInstance.getMap("cart");
	        
	        OrderEntity.put(order.getId(), order);
	        
	        return order;
    	}catch(Exception e) {
    		logger.error("Error in OrderBusinessService update:"+e.getMessage());
    	}
    	return null;
    }

    @Override
    public void delete(Long id) {
    	logger.info("Entering OrderBusinessService delete");
    	try {
	    	IMap<Long, OrderEntity> map = hazelcastInstance.getMap("cart");
	    	map.removeAsync(id);
	        orderRepository.deleteById(id);
    	}catch(Exception e) {
    		logger.error("Error in OrderBusinessService delete:"+e.getMessage());
    	}
    }
}
