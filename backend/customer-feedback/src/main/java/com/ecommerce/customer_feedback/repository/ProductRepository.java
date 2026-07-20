package com.ecommerce.customer_feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.customer_feedback.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
