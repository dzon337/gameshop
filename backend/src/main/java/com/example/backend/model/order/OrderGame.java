package com.example.backend.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.Objects;
import jakarta.persistence.*;
import com.example.backend.model.game.Game;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="OrderGame")
@Table(name="order_games")
@ToString

public class OrderGame {
    @EmbeddedId
    @Column(name="order_game_id")
    private OrderGameKey orderGameId;

    @ManyToOne
    @MapsId("orderId")
    @JsonIgnore

    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name="quantity")
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