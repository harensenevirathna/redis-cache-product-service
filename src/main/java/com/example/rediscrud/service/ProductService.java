package com.example.rediscrud.service;

import com.example.rediscrud.dto.Product;

public interface ProductService {
    public void productCreate(String productName) throws Exception;
    public Product getProduct(String product) throws Exception;
}
