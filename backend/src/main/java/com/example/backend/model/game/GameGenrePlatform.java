package com.example.backend.model.game;

import java.util.Set;
import com.example.backend.model.genre.EGenreName;
import com.example.backend.model.platform.EPlatformName;

/**
 * This class is mainly used to tie each game with its corresponding
 * genres and platforms on which it is available. Used in the database filling service.
 */
public record GameGenrePlatform(Game game, Set<EGenreName> genres, Set<EPlatformName> platforms) {

}