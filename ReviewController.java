package com.bephathao.controller;

import com.bephathao.entity.Review;
import com.bephathao.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private IReviewService reviewService;

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getAllByProductId(@PathVariable Long productId,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "5") int size) {
        try {
            return ResponseEntity.ok(reviewService.findAllByProductId(productId, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/count/{productId}")
    public ResponseEntity<Object> countAllByProductId(@PathVariable Long productId){
        try {
            return ResponseEntity.ok(reviewService.totalReviews(productId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/avg-rating/{productId}")
    public ResponseEntity<Object> averageRatingByProductId(@PathVariable Long productId){
        try {
            return ResponseEntity.ok(reviewService.averageRatingByProductId(productId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createReview(@RequestBody Review review){
        try {
            return ResponseEntity.ok(reviewService.createReview(review));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
