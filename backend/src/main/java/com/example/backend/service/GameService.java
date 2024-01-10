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
        final Optional<Game> game = gameRepository.findById(gameId);

        if(game.isPresent()) {
            return game.get();
        }
        else {
            throw new GameDoesNotExistException("Game not found for id :: " + gameId);
        }
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game addGame(final Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(final Long gameId, final Game gameDetails) {
        final Game game = getGameById(gameId);

        return gameRepository.save(game);
    }

    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }
}