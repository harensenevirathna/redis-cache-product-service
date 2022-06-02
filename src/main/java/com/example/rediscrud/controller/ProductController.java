package com.example.rediscrud.controller;

import com.example.rediscrud.dto.Product;
import com.example.rediscrud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product-create")
    public ResponseEntity<Void> productCreate(@RequestParam String itemName) throws Exception {
        productService.productCreate(itemName);
        log.info("product created");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/product-info")
    public ResponseEntity<Product> getProduct(@RequestParam String itemName) throws Exception {
        Product product = productService.getProduct(itemName);
        return ResponseEntity.ok(product);
    }
}
