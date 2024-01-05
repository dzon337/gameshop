package com.example.backend.model.platform;

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
@Entity(name="Platform")
@Table(
        name = "platforms",
        uniqueConstraints = {
                @UniqueConstraint(name="platform_name_unique", columnNames = "platform_name")
        }
)
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id", updatable = false)
    private Long platform_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false)
    private EManufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform_name", nullable = false)
    private EPlatformName platformName;

    /**
     * @param manufacturer The manufacturing company.
     * @param platformName The name of the platform itself - Playstation 4 for example.
     */
    public Platform(final EManufacturer manufacturer, final EPlatformName platformName) {
        this.manufacturer = manufacturer;
        this.platformName = platformName;
    }

    /**
     * Link to games. (N:M Game and Platform)
     */
    @ManyToMany(mappedBy = "platforms")
    @JsonBackReference
    @Builder.Default
    private Set<Game> games = new HashSet<>();

    /**
     * When creating or adding a game we need to map it to at least one platform.
     * This means a game is available on a given platform.
     * @param game A new game.
     */
    public void addGame(final Game game) {
        this.games.add(game);
    }

    /**
     * @param o Instance of another Platform object.
     * @return If both the manufacturer and the platform are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform platform = (Platform) o;
        return this.manufacturer == platform.manufacturer && this.platformName == platform.platformName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.manufacturer, this.platformName);
    }
}