package com.ecommerce.customer_feedback.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.customer_feedback.entity.Review;
import com.ecommerce.customer_feedback.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping("/add/{userId}/{orderItemId}")
    public ResponseEntity<Review> addReview(
            @PathVariable Long userId,
            @PathVariable Long orderItemId,
            @RequestBody Review review) {

        Review savedReview = reviewService.addReview(userId, orderItemId, review);

        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }


    @PutMapping("/update/{userId}/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long userId,
            @PathVariable Long reviewId,
            @RequestBody Review review) {

        Review updatedReview = reviewService.updateReview(userId, reviewId, review);

        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{userId}/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long userId,
            @PathVariable Long reviewId) {

        reviewService.deleteReview(userId, reviewId);

        return ResponseEntity.ok("Review deleted successfully");
    }


    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable Long reviewId) {

        Review review = reviewService.getReviewById(reviewId);

        return ResponseEntity.ok(review);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(
            @PathVariable Long productId) {

        List<Review> reviews = reviewService.getReviewsByProduct(productId);

        return ResponseEntity.ok(reviews);
    }


    @GetMapping("/product/{productId}/average")
    public ResponseEntity<Double> getAverageRating(
            @PathVariable Long productId) {

        Double averageRating = reviewService.getAverageRating(productId);

        return ResponseEntity.ok(averageRating);
    }

}
