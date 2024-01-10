package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.game.Game;
import com.example.backend.model.request.UpdateGameRequest;
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
        return gameRepository.save(game);
    }

    public Game updateGame(final Long gameId, final UpdateGameRequest gameDetails) {
        final Game game = getGameById(gameId);

        game.setPrice(gameDetails.getNewPrice());
        game.setGameName(gameDetails.getNewName());

        return gameRepository.save(game);
    }

    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }
}