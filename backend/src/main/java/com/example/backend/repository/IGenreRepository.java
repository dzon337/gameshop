package com.example.backend.repository;

import java.util.Optional;

import com.example.backend.model.genre.Genre;
import com.example.backend.model.genre.EGenreName;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IGenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findGenreByGenreName(final EGenreName genreName);

}
