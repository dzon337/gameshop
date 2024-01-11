package com.example.backend.model.review;

import lombok.*;

import java.util.Objects;

import jakarta.persistence.*;

import com.example.backend.model.game.Game;
import com.example.backend.model.user.User;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="GameReview")
@Table(name="game_reviews")
public class GameReview {

    private static Long GAME_REVIEW_ID = 0L;
    public final static Integer MIN_GAME_REVIEW_RATING = 1;
    public final static Integer MAX_GAME_REVIEW_RATING = 10;

    @EmbeddedId
    @Column(name="game_review_id", updatable = false)
    private GameReviewKey gameReviewId;

    @Column(name="rating", nullable = false)
    private Integer rating;

    @Column(name="text", nullable = false)
    private String reviewText;

    @ManyToOne
    @MapsId("gameId")
    @JsonBackReference
    @JoinColumn(name = "game_id", nullable = false)
    @ToString.Exclude
    private Game game;
    public void setGame(final Game game) {this.game = game;}

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
    public void setUser(final User user) {
        this.user = user;
    }

    public static Long getID() {
        return ++GAME_REVIEW_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameReview other = (GameReview) o;
        return Objects.equals(gameReviewId, other.gameReviewId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameReviewId);
    }
}