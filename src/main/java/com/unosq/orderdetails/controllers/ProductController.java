package com.unosq.orderdetails.controllers;

import com.unosq.orderdetails.entities.Product;
import com.unosq.orderdetails.services.ProductService;
import org.hibernate.dialect.pagination.LimitOffsetLimitHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();

            if (!products.isEmpty()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error");
        }
    }

    @GetMapping("product/{uuid}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID uuid) {
        try {
            Optional<Product> product = productService.getProduct(uuid);

            return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error");
        }
    }

    @PostMapping("products")
    public ResponseEntity<Product> addOrder(@RequestBody Product product) {
        try {
            Product productSaved = productService.addProduct(product);

            return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A Bad Request");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error");
        }
    }
}
