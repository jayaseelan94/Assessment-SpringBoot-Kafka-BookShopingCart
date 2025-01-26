package com.example.shoppingcart.cart.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
@EnableJpaRepositories
@EnableCaching
public class HazelcastConfig {
	
	@Bean
	public HazelcastInstance hazelcastInstance() {
		Config config = new Config();
		
		MapConfig mapConfig = new MapConfig();
		
		mapConfig.setName("cart");
		mapConfig.setTimeToLiveSeconds(6000);

		config.setInstanceName("cart_hazelcast");
		config.addMapConfig(mapConfig);
		return Hazelcast.newHazelcastInstance(config);
	}
}
