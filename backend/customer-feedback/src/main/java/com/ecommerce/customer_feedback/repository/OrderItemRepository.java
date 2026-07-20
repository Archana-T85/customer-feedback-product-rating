package com.ecommerce.customer_feedback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.customer_feedback.entity.OrderItem;
import com.ecommerce.customer_feedback.enums.OrderStatus;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

	Optional<OrderItem> findById(Long orderItemId);
	 Optional<OrderItem> findByOrderUserUserIdAndProductProductIdAndOrderOrderStatus(
	            Long userId,
	            Long productId,
	            OrderStatus orderStatus);
}
