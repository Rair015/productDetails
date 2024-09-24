package com.unosq.orderdetails.controllers;

import com.unosq.orderdetails.entities.Product;
import com.unosq.orderdetails.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("product/{uuid}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID uuid) {
        return productService.getProduct(uuid);
    }

    @PostMapping("products")
    public ResponseEntity<String> addOrder(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
