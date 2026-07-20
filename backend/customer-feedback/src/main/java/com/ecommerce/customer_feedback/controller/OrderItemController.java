package com.ecommerce.customer_feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.customer_feedback.entity.OrderItem;
import com.ecommerce.customer_feedback.repository.OrderItemRepository;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;


    @PostMapping
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {

        return orderItemRepository.save(orderItem);
    }


    @GetMapping
    public List<OrderItem> getAllOrderItems() {

        return orderItemRepository.findAll();
    }


    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {

        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Item not found"));
    }
    @GetMapping("/user/{userId}/product/{productId}")
    public OrderItem getOrderItemByUserAndProduct(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        return orderItemRepository
                .findByOrderUserUserIdAndProductProductIdAndOrderOrderStatus(
                        userId,
                        productId,
                        com.ecommerce.customer_feedback.enums.OrderStatus.DELIVERED)
                .orElseThrow(() -> new RuntimeException("No delivered order found"));
    }

}
