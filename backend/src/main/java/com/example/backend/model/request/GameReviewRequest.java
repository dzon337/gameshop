package com.example.backend.model.request;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
public class GameReviewRequest {
    private Long userId;            // Person who uploaded the review.
    private Long gameId;            // ID of the game to which the review refers to.
    private Integer rating;         // The rating of the game left by the reviewer.
    private String reviewText;      // What the reviewer wrote about this particular game.
}