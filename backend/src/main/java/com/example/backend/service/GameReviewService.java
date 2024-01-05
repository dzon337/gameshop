package com.example.backend.service;

import com.example.backend.model.review.GameReview;
import com.example.backend.repository.IGameReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameReviewService {

    @Autowired
    private IGameReview gameReviewRepository;

    public List<GameReview> getReviewsByGameId(Long gameId) {
        return gameReviewRepository.findByGameReviewIdGameId(gameId);
    }
}

