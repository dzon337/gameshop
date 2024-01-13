package com.example.backend.model.review;

import lombok.*;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Data
@Builder
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GameReviewKey implements Serializable {

    @Column(name="game_id")
    private Long gameId;

    @Column(name="review_id")
    private Long reviewId;

}