package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.game.Game;
import com.example.backend.model.request.UpdateGameReviewRequest;
import com.example.backend.service.GameService;
import com.example.backend.model.review.GameReview;
import com.example.backend.service.GameReviewService;
import com.example.backend.model.request.GameReviewRequest;

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

    // Get a game by ID
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable("gameId") Long gameId) {
        Game game = gameService.getGameById(gameId);
        return ResponseEntity.ok(game);
    }

    // Get all games
    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{gameId}/reviews")
    public ResponseEntity<List<GameReview>> getReviewsByGameId(@PathVariable Long gameId) {
        List<GameReview> reviews = gameReviewService.getReviewsByGameId(gameId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping(value = "/{gameId}/uploadReview")
    public ResponseEntity<GameReview> uploadReview(@PathVariable Long gameId, @RequestBody GameReviewRequest reviewRequest) {
        try {
            final GameReview newGameReview = this.gameReviewService.uploadGameReview(gameId, reviewRequest);
            return new ResponseEntity<>(newGameReview, HttpStatus.OK);
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(new GameReview(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{gameId}+{reviewId}/deleteReview")
    public ResponseEntity<GameReview> deleteReview(@PathVariable Long gameId, @PathVariable Long reviewId) {
        final Optional<GameReview> possibleDeletedReview = this.gameReviewService.deleteReview(gameId, reviewId);

        if(possibleDeletedReview.isPresent()) {
            return new ResponseEntity<>(possibleDeletedReview.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new GameReview(), HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/{gameId}+{reviewId}/updateReview")
    public ResponseEntity<GameReview> deleteReview(@PathVariable Long gameId, @PathVariable Long reviewId, @RequestBody UpdateGameReviewRequest request) {
        final Optional<GameReview> possibleDeletedReview = this.gameReviewService.updateReview(gameId, reviewId, request);

        if(possibleDeletedReview.isPresent()) {
            return new ResponseEntity<>(possibleDeletedReview.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new GameReview(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game newGame = gameService.addGame(game);
        return ResponseEntity.ok(newGame);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable("gameId") Long gameId, @RequestBody Game gameDetails) {
        Game updatedGame = gameService.updateGame(gameId, gameDetails);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.ok().build();
    }

}