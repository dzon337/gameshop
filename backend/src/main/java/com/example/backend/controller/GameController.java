package com.example.backend.controller;

import java.util.List;

import com.example.backend.model.game.Game;
import com.example.backend.service.GameService;
import com.example.backend.model.review.GameReview;
import com.example.backend.service.GameReviewService;
import com.example.backend.model.request.GameReviewRequest;
import com.example.backend.model.request.UpdateGameRequest;
import com.example.backend.model.request.UpdateGameReviewRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameReviewService gameReviewService;

    @GetMapping("/{gameId}")
    public ResponseEntity<?> getGameById(@PathVariable("gameId") Long gameId) {
        try {
            final Game game = this.gameService.getGameById(gameId);
            return ResponseEntity.ok(game);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGames() {
        final List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @PostMapping(value="/addGame")
    public ResponseEntity<?> addGame(@RequestBody Game game) {
        try {
            final Game newGame = gameService.addGame(game);
            return ResponseEntity.ok(newGame);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<?> updateGame(@PathVariable("gameId") Long gameId, @RequestBody UpdateGameRequest gameDetails) {
        try {
            final Game updatedGame = gameService.updateGame(gameId, gameDetails);
            return ResponseEntity.ok(updatedGame);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
    }

    @GetMapping("/{gameId}/reviews")
    public ResponseEntity<List<GameReview>> getReviewsByGameId(@PathVariable Long gameId) {
        final List<GameReview> reviews = gameReviewService.getReviewsByGameId(gameId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping(value = "/{gameId}/uploadReview")
    public ResponseEntity<String> uploadReview(@PathVariable Long gameId, @RequestBody GameReviewRequest reviewRequest) {
        try {
            final String postResult = this.gameReviewService.uploadGameReview(gameId, reviewRequest);
            return new ResponseEntity<>(postResult, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/gameId={gameId}+reviewId={reviewId}/deleteReview")
    public ResponseEntity<String> deleteReview(@PathVariable Long gameId, @PathVariable Long reviewId) {
        try {
            final String deleteResult = this.gameReviewService.deleteReview(gameId, reviewId);
            return ResponseEntity.ok(deleteResult);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value="/gameId={gameId}+reviewId={reviewId}/updateReview")
    public ResponseEntity<String> deleteReview(@PathVariable Long gameId, @PathVariable Long reviewId, @RequestBody UpdateGameReviewRequest request) {
        try {
            final String putResult = this.gameReviewService.updateReview(gameId, reviewId, request);
            return ResponseEntity.ok(putResult);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}