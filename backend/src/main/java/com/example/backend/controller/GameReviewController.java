package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend.model.review.GameReview;
import com.example.backend.service.GameReviewService;
import com.example.backend.model.request.GameReviewRequest;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class GameReviewController {

    @Autowired
    private GameReviewService gameReviewService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<GameReview>> getAllGameReviews() {
        final List<GameReview> reviews = this.gameReviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<GameReview> uploadReview(@RequestBody GameReviewRequest gameReview) {
        final GameReview newGameReview = this.gameReviewService.uploadGameReview(gameReview);
        return new ResponseEntity<>(newGameReview, HttpStatus.OK);
    }

}