package com.example.backend.service;


import com.example.backend.model.game.Game;
import com.example.backend.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private IGameRepository gameRepository;

    // Get a game by ID
    public Game getGameById(Long gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if(game.isPresent()) {
            return game.get();
        } else {
            // Handle the case where the game is not found
            // Throw an exception or return null based on your design
            throw new RuntimeException("Game not found for id :: " + gameId);
        }
    }

    // Get all games
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Add a new game
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    // Update a game
    public Game updateGame(Long gameId, Game gameDetails) {
        Game game = getGameById(gameId);
        // Update game attributes here, e.g., game.setName(gameDetails.getName());
        return gameRepository.save(game);
    }

    // Delete a game
    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }
}
