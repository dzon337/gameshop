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
    @Column(name = "platform_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platform_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false)
    private EManufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform_name", nullable = false)
    private EPlatformName platformName;

    public Platform(final EManufacturer manufacturer, final EPlatformName platformName) {
        this.manufacturer = manufacturer;
        this.platformName = platformName;
    }

    @ManyToMany(mappedBy = "platforms")
    @JsonBackReference
    @Builder.Default
    private Set<Game> games = new HashSet<>();
    public void addGame(final Game game) {
        this.games.add(game);
    }

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