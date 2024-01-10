package com.example.backend.model.review;

import lombok.*;

import java.util.Objects;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Data
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class GameReviewKey implements Serializable {

    @Column(name="game_id")
    private Long gameId;

    @Column(name="review_id")
    private Long reviewId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameReviewKey that = (GameReviewKey) o;
        return Objects.equals(gameId, that.gameId) &&
                Objects.equals(reviewId, that.reviewId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.gameId, this.reviewId);
    }
}