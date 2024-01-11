package com.example.backend.model.order;

import lombok.*;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Data
@Builder
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderGameKey implements Serializable {

    @Column(name="order_id")
    private Long orderId;

    @Column(name="game_id")
    private Long gameId;

}