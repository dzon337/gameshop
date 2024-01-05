package com.example.backend.repository;

import java.util.Optional;
import com.example.backend.model.genre.Genre;
import org.springframework.stereotype.Repository;
import com.example.backend.model.genre.EGenreName;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IGenreRepository extends JpaRepository<Genre, Long> {

    // Returns the corresponding Genre object with this name. Used when adding the games.
    Optional<Genre> findGenreByGenreName(final EGenreName genreName);
}
