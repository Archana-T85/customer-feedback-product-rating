package com.ecommerce.customer_feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.customer_feedback.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
