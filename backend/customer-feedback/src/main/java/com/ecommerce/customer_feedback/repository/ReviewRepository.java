package com.ecommerce.customer_feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.customer_feedback.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

	List<Review> findByOrderItemProductProductId(Long productId);
}
