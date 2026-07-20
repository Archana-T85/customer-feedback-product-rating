package com.ecommerce.customer_feedback.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.customer_feedback.entity.OrderItem;
import com.ecommerce.customer_feedback.entity.Review;
import com.ecommerce.customer_feedback.enums.OrderStatus;
import com.ecommerce.customer_feedback.repository.OrderItemRepository;
import com.ecommerce.customer_feedback.repository.ReviewRepository;

@Service
public class ReviewService {

	    @Autowired
	    private ReviewRepository reviewRepository;

	    @Autowired
	    private OrderItemRepository orderItemRepository;

	    
	    public Review addReview(Long userId, Long orderItemId, Review review) {

	        OrderItem orderItem = orderItemRepository.findById(orderItemId)
	                .orElseThrow(() -> new RuntimeException("Order Item not found"));

	        if (!orderItem.getOrder().getUser().getUserId().equals(userId)) {
	            throw new RuntimeException("You are not authorized to review this product.");
	        }

	        if (orderItem.getOrder().getOrderStatus() != OrderStatus.DELIVERED) {
	            throw new RuntimeException("Review can only be added for delivered orders.");
	        }

	        if (orderItem.getReview() != null) {
	            throw new RuntimeException("Review already exists for this order item.");
	        }
	        if(review.getRating() < 1 || review.getRating() > 5) {
	            throw new RuntimeException("Rating must be between 1 and 5");
	        }

	        review.setOrderItem(orderItem);
	        review.setReviewDate(LocalDateTime.now());

	        return reviewRepository.save(review);
	    }

	    
	    public Review updateReview(Long userId, Long reviewId, Review review) {

	        Review existingReview = reviewRepository.findById(reviewId)
	                .orElseThrow(() -> new RuntimeException("Review not found"));

	        if (!existingReview.getOrderItem().getOrder().getUser().getUserId().equals(userId)) {
	            throw new RuntimeException("You are not authorized to update this review.");
	        }

	        existingReview.setRating(review.getRating());
	        existingReview.setReviewComment(review.getReviewComment());

	        return reviewRepository.save(existingReview);
	    }

	    
	    public void deleteReview(Long userId, Long reviewId) {

	        Review review = reviewRepository.findById(reviewId)
	                .orElseThrow(() -> new RuntimeException("Review not found"));

	        if (!review.getOrderItem().getOrder().getUser().getUserId().equals(userId)) {
	            throw new RuntimeException("You are not authorized to delete this review.");
	        }

	        reviewRepository.delete(review);
	    }

	    
	    public Review getReviewById(Long reviewId) {

	        return reviewRepository.findById(reviewId)
	                .orElseThrow(() -> new RuntimeException("Review not found"));
	    }

	   
	    public List<Review> getReviewsByProduct(Long productId) {

	        return reviewRepository.findByOrderItemProductProductId(productId);
	    }

	   
	    public Double getAverageRating(Long productId) {

	        List<Review> reviews = reviewRepository.findByOrderItemProductProductId(productId);

	        if (reviews.isEmpty()) {
	            return 0.0;
	        }

	        return reviews.stream()
	                .mapToInt(Review::getRating)
	                .average()
	                .orElse(0.0);
	    }
	

}
