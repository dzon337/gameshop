package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.game.Game;
import com.example.backend.service.GameService;
import com.example.backend.model.review.GameReview;
import com.example.backend.service.GameReviewService;
import com.example.backend.model.request.PutGameReviewRequest;
import com.example.backend.model.request.PostGameReviewRequest;

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
    public ResponseEntity<?> updateGame(@PathVariable("gameId") Long gameId, @RequestBody Game game) {
        try {
            final Game updatedGame = gameService.updateGame(gameId, game);
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


    // Relevant for reviews.
    @GetMapping("/{gameId}/reviews")
    public ResponseEntity<List<GameReview>> getReviewsByGameId(@PathVariable Long gameId) {
        final List<GameReview> reviews = gameReviewService.getReviewsByGameId(gameId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{gameId}/reviews/{username}")
    public ResponseEntity<?> getReviewsByGameIdAndUsername(@PathVariable Long gameId, @PathVariable String username) {
        try {
            final List<GameReview> gameReviews = gameReviewService.getReviewsByGameId(gameId);
            final Optional<GameReview> possibleUserReview = gameReviews
                    .stream()
                    .filter(review -> review.getUser().getUsername().equals(username))
                    .findFirst();

            if(possibleUserReview.isPresent()) {
                return ResponseEntity.ok(possibleUserReview.get());
            }

            final String msg = "Review for gameId: [" + gameId + "] and username: [" + username + "] not found!";
            return ResponseEntity.badRequest().body(msg);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/{gameId}/uploadReview/{username}")
    public ResponseEntity<String> uploadReview(@PathVariable Long gameId, @PathVariable String username,  @RequestBody PostGameReviewRequest reviewRequest) {
        try {
            final String postResult = this.gameReviewService.uploadGameReview(gameId, username, reviewRequest);
            return ResponseEntity.ok(postResult);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{gameId}/deleteReview/{username}")
    public ResponseEntity<String> deleteReview(@PathVariable Long gameId, @PathVariable String username) {
        try {
            final String deleteResult = this.gameReviewService.deleteReview(gameId, username);
            return ResponseEntity.ok(deleteResult);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value="/{gameId}/updateReview/{username}")
    public ResponseEntity<String> updateReview(@PathVariable Long gameId, @PathVariable String username, @RequestBody PutGameReviewRequest request) {
        try {
            final String putResult = this.gameReviewService.updateReview(gameId, username, request);
            return ResponseEntity.ok(putResult);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}