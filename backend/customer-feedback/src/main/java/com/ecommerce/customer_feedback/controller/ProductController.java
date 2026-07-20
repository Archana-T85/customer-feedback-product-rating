package com.ecommerce.customer_feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.customer_feedback.entity.Product;
import com.ecommerce.customer_feedback.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    public Product addProduct(@RequestBody Product product) {

        return productRepository.save(product);
    }


    @GetMapping
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}