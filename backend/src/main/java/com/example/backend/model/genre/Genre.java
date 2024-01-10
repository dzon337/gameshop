package com.example.backend.model.genre;

import lombok.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

import jakarta.persistence.*;

import com.example.backend.model.game.Game;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Genre")
@Table(
        name = "genres",
        uniqueConstraints = {
        @UniqueConstraint(name="genre_name_unique", columnNames = "genre_name")}
)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", updatable = false)
    private Long genreId;

    @Column (name = "genre_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column (name = "genre_name", nullable = false)
    private EGenreName genreName;

    public Genre(final String description, final EGenreName genreName) {
        this.description = description;
        this.genreName = genreName;
    }

    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    @Builder.Default // Initializes the Set of Game objects automatically.
    private Set<Game> games = new HashSet<>();

    public void addGame(final Game game) {
        this.games.add(game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return this.genreName.equals(genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.genreName);
    }
}