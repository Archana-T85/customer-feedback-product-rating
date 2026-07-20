package com.ecommerce.customer_feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.customer_feedback.entity.User;
import com.ecommerce.customer_feedback.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public User addUser(@RequestBody User user) {

        return userRepository.save(user);
    }


    @GetMapping
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}