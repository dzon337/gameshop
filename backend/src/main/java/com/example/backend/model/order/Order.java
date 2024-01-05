package com.example.backend.model.order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.Collection;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.backend.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Order")
@Table(name = "orders")
public class Order {
    private static Long ORDER_ID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false)
    private Long orderId;

    @Column(name="shipping_method", nullable = false)
    private EShippingMethod shippingMethod;

    @Column (name = "order_date", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime order_date;

    // One user places an order.
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @Builder.Default
    private Set<OrderGame> orderGames = new HashSet<>();

    public void addOrderGames(final Collection<OrderGame> orderGames) {
        this.orderGames.addAll(orderGames);
    }

    public static Long getID() {
        return ++ORDER_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}