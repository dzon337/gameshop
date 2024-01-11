package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.game.Game;
import com.example.backend.repository.IGameRepository;
import com.example.backend.exceptions.GameDoesNotExistException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameService {

    @Autowired
    private IGameRepository gameRepository;

    public Game getGameById(final Long gameId) {
        final Optional<Game> possibleGame = gameRepository.findById(gameId);

        if(possibleGame.isPresent()) {
            return possibleGame.get();
        }
        else {
            throw new GameDoesNotExistException("Game not found for ID: " + gameId);
        }
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game addGame(final Game game) {
        final Optional<Game> possibleGame = this.gameRepository.findGameByGameName(game.getGameName());
        if(possibleGame.isPresent()) {
            throw new RuntimeException("Game " + game.getGameName() + " already present!");
        }

        this.gameRepository.save(game);

        return game;
    }

    public Game updateGame(final Long gameId, final Game newGame) {
        final Optional<Game> possibleGame = this.gameRepository.findById(gameId);
        if(possibleGame.isEmpty()) {
            throw new GameDoesNotExistException("Game " + gameId + " not found!");
        }

        final Game updatedGame = possibleGame.get();
        updatedGame.setGameName(newGame.getGameName());
        updatedGame.setPrice(newGame.getPrice());

        gameRepository.save(updatedGame);

        return updatedGame;
    }

    public void deleteGame(final Long gameId) {
        gameRepository.deleteById(gameId);
    }

}