package com.unosq.orderdetails.services;

import com.unosq.orderdetails.entities.Product;
import com.unosq.orderdetails.repositories.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductService {
    private static final Logger log = Logger.getLogger(ProductService.class.getName());
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(UUID uuid) {
        return productRepository.findById(uuid);
    }

    public Product addProduct(Product product) {
        try {
            product = productRepository.save(product);
            return product;
        } catch (DataAccessException e) {
            log.severe("Ops!");
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }
}
