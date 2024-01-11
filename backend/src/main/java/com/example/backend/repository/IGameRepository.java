package com.example.backend.repository;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.game.Game;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findGameByGameName(final String gameName);

    @Query(nativeQuery = true, value = "SELECT g.game_name, g.game_price, ge.genre_name, SUM(og.quantity) AS sold_copies,\n" +
            "SUM(og.quantity * g.game_price) AS total_profit FROM orders o JOIN order_games og\n" +
            "ON o.order_id = og.order_id JOIN games g ON g.game_id = og.game_id JOIN game_genre gg\n" +
            "ON gg.game_id = g.game_id JOIN genres ge ON ge.genre_id = gg.genre_id GROUP BY g.game_name, g.game_price,\n" +
            "ge.genre_id ORDER BY total_profit DESC")
    List<Object[]> bestSellerGames();

}