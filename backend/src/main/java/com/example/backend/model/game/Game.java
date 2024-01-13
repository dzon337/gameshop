package com.example.backend.model.game;

import lombok.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.Collection;

import jakarta.persistence.*;

import com.example.backend.model.genre.Genre;
import com.example.backend.model.order.OrderGame;
import com.example.backend.model.review.GameReview;
import com.example.backend.model.platform.Platform;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Game")
@Table(
        name="games",
        uniqueConstraints = {
                @UniqueConstraint(name="game_name_unique", columnNames = "name")
        }
)
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", updatable = false)
    private Long gameId;

    @Column(name="game_name", nullable = false)
    private String gameName;

    @Column(name="game_price", nullable = false)
    private Float price;

    public Game(final String name, final Float price) {
        this.gameName = name;
        this.price = price;
    }

    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonManagedReference
    @Builder.Default
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();
    public void addGenres(final Collection<Genre> genres) {
        this.genres.addAll(genres);
    }

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    @JsonManagedReference
    @Builder.Default
    @ToString.Exclude
    private Set<Platform> platforms = new HashSet<>();
    public void addPlatforms(final Collection<Platform> platforms) {
        this.platforms.addAll(platforms);
    }

    @OneToMany(mappedBy = "game")
    @JsonManagedReference
    @Builder.Default
    @ToString.Exclude
    private Set<GameReview> reviews = new HashSet<>();
    public void addReview(final GameReview review) {
        this.reviews.add(review);
    }

    @OneToMany(mappedBy = "game")
    @JsonBackReference
    @Builder.Default
    @ToString.Exclude
    private Set<OrderGame> orderGames = new HashSet<>();
    public void addOrderGames(final Collection<OrderGame> orderGames) {
        this.orderGames.addAll(orderGames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game otherGame = (Game) o;
        return Objects.equals(this.gameName.toLowerCase(), otherGame.gameName.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.gameName);
    }
}