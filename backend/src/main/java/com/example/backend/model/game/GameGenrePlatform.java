package com.example.backend.model.game;

import java.util.Set;

import com.example.backend.model.genre.EGenreName;
import com.example.backend.model.platform.EPlatformName;

public record GameGenrePlatform(Game game, Set<EGenreName> genres, Set<EPlatformName> platforms) { }