package com.example.backend.model.request;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class GameReviewRequest {
    private String username;
    private Integer rating;
    private String reviewText;
}