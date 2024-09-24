package com.unosq.orderdetails.services;

import com.unosq.orderdetails.entities.Product;
import com.unosq.orderdetails.repositories.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductService {
    private static final Logger log = Logger.getLogger(ProductService.class.getName());
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Product> getProduct(UUID uuid) {
        try {
            return new ResponseEntity<>(productRepository.findById(uuid).orElseThrow(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> addProduct(Product product) {
        try {
            productRepository.save(product);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (DataAccessException e) {
            log.severe("Ops!");
            log.severe(e.getLocalizedMessage());
            return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
        }
    }
}
