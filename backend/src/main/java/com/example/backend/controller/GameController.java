package com.example.backend.controller;

import com.example.backend.model.game.Game;
import com.example.backend.model.review.GameReview;
import com.example.backend.service.GameReviewService;
import com.example.backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameReviewService gameReviewService;
    @Autowired
    private GameService gameService;

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

    // Add a new game
    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game newGame = gameService.addGame(game);
        return ResponseEntity.ok(newGame);
    }

    // Update a game
    @PutMapping("/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable("gameId") Long gameId, @RequestBody Game gameDetails) {
        Game updatedGame = gameService.updateGame(gameId, gameDetails);
        return ResponseEntity.ok(updatedGame);
    }

    // Delete a game
    @DeleteMapping("/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{gameId}/reviews")
    public ResponseEntity<List<GameReview>> getReviewsByGameId(@PathVariable Long gameId) {
        List<GameReview> reviews = gameReviewService.getReviewsByGameId(gameId);
        return ResponseEntity.ok(reviews);
    }
}
