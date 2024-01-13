package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.game.Game;
import com.example.backend.model.user.User;
import com.example.backend.model.review.GameReview;
import com.example.backend.model.review.GameReviewKey;
import com.example.backend.repository.IGameRepository;
import com.example.backend.repository.IUserRepository;
import com.example.backend.repository.IGameReviewRepository;
import com.example.backend.exceptions.UserNotFoundException;
import com.example.backend.model.request.PutGameReviewRequest;
import com.example.backend.model.request.PostGameReviewRequest;
import com.example.backend.exceptions.GameDoesNotExistException;
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

    public String uploadGameReview(final Long gameId, final String username, final PostGameReviewRequest request) {
        final Optional<User> possibleUser = userRepo.findUserByUsername(username);
        if(possibleUser.isEmpty()) {
            final String errMsg = "User: [" + username + "] does not exist!";
            throw new UserNotFoundException(errMsg);
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
                    final String errMsg = username + " has already uploaded a review about this game: " + gameId + "!";
                    throw new UserAlreadyUploadedGameReviewForThisGameException(errMsg);
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

        return username + " added GameReview: " + gameReview + "!";
    }

    public String deleteReview(final Long gameId, final String username) {
        final List<GameReview> gameIdReviews = this.gameReviewRepo.findByGameReviewIdGameId(gameId);

        final Optional<GameReview> possibleUserReview = gameIdReviews
                .stream()
                .filter(review -> review.getUser().getUsername().equals(username))
                .findFirst();

        if(possibleUserReview.isPresent()) {
            final GameReview toBeDeleted = possibleUserReview.get();
            this.gameReviewRepo.deleteById(toBeDeleted.getGameReviewId());
            return "Deleted the GameReview: " + toBeDeleted + "!";
        }

        return "GameReview was not found for: [gameId=" + gameId + ", username=" + username + "]!";
    }

    public String updateReview(final Long gameId, final String username, final PutGameReviewRequest request) {
        final String newReviewText = request.getNewReviewText();
        if(newReviewText.isEmpty()) {
            throw new RuntimeException("PUT API call with empty newReviewText!");
        }

        final Integer newRating = request.getNewRating();
        if(newRating < GameReview.MIN_GAME_REVIEW_RATING || newRating > GameReview.MAX_GAME_REVIEW_RATING) {
            throw new RuntimeException("PUT API call with an invalid newRating value: " + newRating + "!");
        }

        final List<GameReview> gameIdReviews = this.gameReviewRepo.findByGameReviewIdGameId(gameId);
        final Optional<GameReview> possibleUserReview = gameIdReviews
                .stream()
                .filter(review -> review.getUser().getUsername().equals(username))
                .findFirst();

        if(possibleUserReview.isEmpty()) {
            return "Update didn't take place. Make sure the provided gameId and username are correct!";
        }

        final GameReview userGameReview = possibleUserReview.get();
        if(userGameReview.getReviewText().equals(newReviewText) && userGameReview.getRating().equals(newRating)) {
            throw new RuntimeException("Cannot update review because the same data is provided!");
        }

        userGameReview.setReviewText(newReviewText);
        userGameReview.setRating(newRating);

        this.gameReviewRepo.save(userGameReview);

        return "Successful update. New GameReview: " + userGameReview + "!";
    }

}