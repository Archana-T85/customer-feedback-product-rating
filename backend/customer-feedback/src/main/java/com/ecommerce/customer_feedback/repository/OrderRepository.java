package com.ecommerce.customer_feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.customer_feedback.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
