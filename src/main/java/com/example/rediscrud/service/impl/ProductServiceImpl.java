package com.example.rediscrud.service.impl;

import com.example.rediscrud.dto.Product;
import com.example.rediscrud.repository.ProductServiceRepository;
import com.example.rediscrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductServiceRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductServiceRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    @CachePut(value = "productService", key = "#productService", condition = "#productService!=null", unless = "#result.id < 12000")
    public void productCreate(String productName) throws Exception {
        Product productObj = new Product();
        productObj.setId(productRepository.getCount() + 1);
        productObj.setProductName(productName);
        productObj.setGenerateTimestamp(new Date());
        productRepository.save(productObj);

    }

    @Override
    @Transactional
    @Cacheable(value = "productService", key = "#productName", condition = "#productName!=null", unless = "#result.id < 12000")
    public Product getProduct(String product) throws Exception {
        return productRepository.findByProductName(product);
    }
}
