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

    /*
     * This constructor is used because the IDs are generated automatically. At every insert of a new game.
     */
    public Game(final String name, final Float price) {
        this.gameName = name;
        this.price = price;
    }

    // Game can have multiple genres. (N:M relationship with genres)
    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonManagedReference
    @Builder.Default
    private Set<Genre> genres = new HashSet<>();

    public void addGenres(final Collection<Genre> genres) {
        this.genres.addAll(genres);
    }

    // Game can be available on multiple platforms. (N:M relationship with platforms.)
    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    @JsonManagedReference
    @Builder.Default
    private Set<Platform> platforms = new HashSet<>();

    public void addPlatforms(final Collection<Platform> platforms) {
        this.platforms.addAll(platforms);
    }

    // All the reviews about a particular game.
    @OneToMany(mappedBy = "game")
    @JsonManagedReference
    @Builder.Default
    private Set<GameReview> reviews = new HashSet<>();

    public void addReview(final GameReview review) {
        this.reviews.add(review);
    }

    // All the orders in which a game is to be found.
    @OneToMany(mappedBy = "game")
    @JsonManagedReference
    @Builder.Default
    private Set<OrderGame> orderGames = new HashSet<>();

    public void addOrderGames(final Collection<OrderGame> orderGames) {
        this.orderGames.addAll(orderGames);
    }

    /**
     * @return True if the name of both objects is equal. If not - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        // Use to lower case to include Gta V and GTA V.
        return Objects.equals(this.gameName.toLowerCase(), game.gameName.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.gameName);
    }
}