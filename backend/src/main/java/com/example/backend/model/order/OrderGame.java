package com.example.backend.model.order;

import lombok.*;

import java.util.Objects;

import jakarta.persistence.*;

import com.example.backend.model.game.Game;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="OrderGame")
@Table(name="order_games")
public class OrderGame {

    @EmbeddedId
    @Column(name="order_game_id")
    private OrderGameKey orderGameId;

    @ManyToOne
    @ToString.Exclude
    @MapsId("orderId")
    @JsonManagedReference
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("gameId")
    @ToString.Exclude
    @JsonManagedReference
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGame orderGame = (OrderGame) o;
        return Objects.equals(this.orderGameId, orderGame.orderGameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.orderGameId);
    }
}