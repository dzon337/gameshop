package com.example.backend.model.order;

import lombok.*;

import java.util.Objects;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderGameKey implements Serializable {

    @Column(name="order_id")
    private Long orderId;

    @Column(name="game_id")
    private Long gameId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGameKey that = (OrderGameKey) o;
        return Objects.equals(this.orderId, that.orderId) && Objects.equals(this.gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.orderId, this.gameId);
    }
}