package com.example.backend.repository;

import com.example.backend.model.game.Game;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {
}
