package com.example.backend.repository;

import org.springframework.stereotype.Repository;
import com.example.backend.model.review.GameReview;
import com.example.backend.model.review.GameReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface IGameReview extends JpaRepository<GameReview, GameReviewKey> {
    List<GameReview> findByGameReviewIdGameId(Long gameId);

}
