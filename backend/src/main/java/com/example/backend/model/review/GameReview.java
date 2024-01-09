package com.example.backend.model.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import java.util.Objects;
import jakarta.persistence.*;
import com.example.backend.model.game.Game;
import com.example.backend.model.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.yaml.snakeyaml.events.Event;

@Data
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="GameReview")
@Table(name="game_reviews")
public class GameReview {
    // Used when inserting GameReview objects in the database. It has to be incremented after every insert.
    private static Long GAME_REVIEW_ID = 0L;
    public final static Integer MIN_GAME_REVIEW_RATING = 1;
    public final static Integer MAX_GAME_REVIEW_RATING = 5;

    @EmbeddedId
    @Column(name="game_review_id", updatable = false)
    // Composite key of weak entity having partial key as well keys of game and user.
    private GameReviewKey gameReviewId;

    @Column(name="rating", nullable = false)
    private Integer rating;

    @Column(name="text", nullable = false)
    private String reviewText;

    // A review can be only about a single game.
    @ManyToOne
    @MapsId("gameId")
    @JsonBackReference
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public void setGame(final Game game) {this.game = game;}

    // A review can be uploaded by only one user.
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
        GameReview that = (GameReview) o;
        return Objects.equals(gameReviewId, that.gameReviewId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameReviewId);
    }
}