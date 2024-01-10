package com.example.backend.repository;

import java.util.List;

import com.example.backend.model.review.GameReview;
import com.example.backend.model.review.GameReviewKey;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IGameReviewRepository extends JpaRepository<GameReview, GameReviewKey> {

    List<GameReview> findByGameReviewIdGameId(final Long gameId);

}