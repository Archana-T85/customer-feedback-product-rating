package com.ecommerce.customer_feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.customer_feedback.entity.Order;
import com.ecommerce.customer_feedback.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;


    @PostMapping
    public Order addOrder(@RequestBody Order order) {

        return orderRepository.save(order);
    }


    @GetMapping
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }
}
