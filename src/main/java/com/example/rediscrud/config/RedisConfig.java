package com.example.rediscrud.config;


import com.example.rediscrud.repository.ProductServiceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;


    @Bean
    public RedisTemplate<String, ProductServiceRepository> redisTemplate() {
        RedisTemplate<String, ProductServiceRepository> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.afterPropertiesSet();
        template.expire("productService", Duration.ofHours(2));
        return template;
    }
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}
