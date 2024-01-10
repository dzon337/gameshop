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
import com.example.backend.model.request.UpdateGameReviewRequest;
import com.example.backend.exceptions.UserAlreadyUploadedGameReviewForThisGameException;

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

    public Optional<GameReview> updateReview(final Long gameId, final Long reviewId, final UpdateGameReviewRequest request) {
        final GameReviewKey compositeKey = GameReviewKey
                .builder()
                .reviewId(reviewId)
                .gameId(gameId)
                .build();

        final Optional<GameReview> possibleGameReview = this.gameReviewRepo.findById(compositeKey);

        possibleGameReview.ifPresent(gameReview -> {
            gameReview.setReviewText(request.getReviewText());
            gameReview.setRating(request.getRating());
            this.gameReviewRepo.save(gameReview);
        });

        return Optional.empty();
    }

    public Optional<GameReview> deleteReview(final Long gameId, final Long reviewId) {
        final GameReviewKey compositeKey = GameReviewKey
                                                    .builder()
                                                    .reviewId(reviewId)
                                                    .gameId(gameId)
                                                    .build();

        final Optional<GameReview> possibleGameReview = this.gameReviewRepo.findById(compositeKey);
        if(possibleGameReview.isPresent()) {
            this.gameReviewRepo.deleteById(compositeKey);
        }

        return possibleGameReview;
    }

    public GameReview uploadGameReview(final Long gameId, final GameReviewRequest request) {
        final Optional<User> possibleUser = userRepo.findUserByUsername(request.getUsername());
        if(possibleUser.isEmpty()) {
            final String errMsg = "User: [" + request.getUsername() + "] does not exist!";
            throw new RuntimeException(errMsg);
        }

        final Optional<Game> possibleGame = gameRepo.findById(gameId);
        if(possibleGame.isEmpty()) {
            final String errMsg = "Game with id: [" + gameId + "] does not exist!";
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

        // Check if user has already uploaded a review about this specific game. If so -> error.
        final User user = possibleUser.get();
        final Optional<GameReview> reviewMayAlreadyExist = user
                .getReviews()
                .stream()
                .filter(review -> review.getGameReviewId().getGameId().equals(gameId))
                .findFirst();

        reviewMayAlreadyExist.ifPresent(review -> {
                    throw new UserAlreadyUploadedGameReviewForThisGameException();
                }
        );

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
                                                    .build();

        game.addReview(gameReview);
        user.addReview(gameReview);

        gameReview.setGame(game);
        gameReview.setUser(user);

        this.gameReviewRepo.save(gameReview);
        this.userRepo.save(user);
        this.gameRepo.save(game);

        return gameReview;
    }

}