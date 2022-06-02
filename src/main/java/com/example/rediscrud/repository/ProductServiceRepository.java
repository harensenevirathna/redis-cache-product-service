package com.example.rediscrud.repository;


import com.example.rediscrud.dto.Product;
import com.example.rediscrud.util.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProductServiceRepository {

    private static final int TIME_MINUTE = 5;
    private final RedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private final HashOperations hashOperations;

    @Autowired
    public ProductServiceRepository(RedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.objectMapper = objectMapper;
    }

    public void save(Product product) {
        redisTemplate.opsForHash().put("productService", product.getProductName(), product);
    }


    public Product findByProductName(String product) {
        Object productObj = hashOperations.get("productService", product);
        if (productObj != null) {
            Product productObjConvertValue = objectMapper.convertValue(productObj, Product.class);
            Boolean expireStatus = DateUtils.validateProductExpire(new Date(), productObjConvertValue.getGenerateTimestamp(), TIME_MINUTE);
            if (expireStatus) {
                delete(productObjConvertValue.getProductName());
                return null;
            } else {
                return productObjConvertValue;
            }
        } else {
            return null;
        }
    }

    public List findAll() {
        return hashOperations.values("productService");
    }

    public Integer getCount() {
        return hashOperations.values("productService").size();
    }

    public void delete(String productname) {
        hashOperations.delete("productService", productname);
    }
}
