package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.game.Game;
import com.example.backend.model.user.User;
import com.example.backend.model.review.GameReview;
import com.example.backend.model.review.GameReviewKey;
import com.example.backend.repository.IGameRepository;
import com.example.backend.repository.IUserRepository;
import com.example.backend.model.request.GameReviewRequest;
import com.example.backend.repository.IGameReviewRepository;
import com.example.backend.exceptions.GameDoesNotExistException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameReviewService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IGameRepository gameRepo;

    @Autowired
    private IGameReviewRepository gameReviewRepo;

    public List<GameReview> getReviewsByGameId(Long gameId) {
        return this.gameReviewRepo.findByGameReviewIdGameId(gameId);
    }

    public List<GameReview> getAllReviews() {
        return this.gameReviewRepo.findAll();
    }

    /*
         First check if user exists.
         Then check if game exists.
         Thirdly Validate review text + rating.
         If all is well add game review to db.
            Create GameReviewKey and GameReview objects.
            Set game and user for GameReview.
            Set review for User and Game.
         Lastly the GameReview object must be returned.
     */
    public GameReview uploadGameReview(final GameReviewRequest request) {
        final Optional<User> possibleUser = userRepo.findById(request.getUserId());
        if(possibleUser.isEmpty()) {
            final String errMsg = "User with id: [" + request.getUserId() + "] does not exist!";
            throw new RuntimeException(errMsg);
        }

        final Optional<Game> possibleGame = gameRepo.findById(request.getGameId());
        if(possibleGame.isEmpty()) {
            final String errMsg = "Game with id: [" + request.getGameId() + "] does not exist!";
            throw new GameDoesNotExistException(errMsg);
        }

        final String reviewText = request.getReviewText();
        if(reviewText.isEmpty()) {
            final String errMsg = "Game review text cannot be empty!";
            throw new RuntimeException(errMsg);
        }

        final int rating = request.getRating();
        if(rating < GameReview.MIN_GAME_REVIEW_RATING || rating > GameReview.MAX_GAME_REVIEW_RATING) {
            final String errMsg = "Valid rating range: [" + GameReview.MIN_GAME_REVIEW_RATING +
                    " - " + GameReview.MAX_GAME_REVIEW_RATING + "], your value was: [" + rating + "]!";
            throw new RuntimeException(errMsg);
        }

        final User user = possibleUser.get();
        final Game game = possibleGame.get();
        final GameReviewKey gameReviewKey = GameReviewKey
                                                    .builder()
                                                    .reviewId(GameReview.getID())
                                                    .gameId(game.getGameId())
                                                    .build();
        final GameReview gameReview = GameReview
                                                    .builder()
                                                    .gameReviewId(gameReviewKey)
                                                    .reviewText(reviewText)
                                                    .rating(rating)
                                                    .user(user)
                                                    .game(game)
                                                    .build();

        user.addReview(gameReview);
        game.addReview(gameReview);

        this.userRepo.save(user);
        this.gameRepo.save(game);

        return this.gameReviewRepo.save(gameReview);
    }

}