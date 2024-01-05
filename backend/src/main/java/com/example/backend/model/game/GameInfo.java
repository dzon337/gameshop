package com.example.backend.model.game;

import java.util.Set;
import java.util.HashSet;
import com.example.backend.model.genre.EGenreName;
import com.example.backend.model.platform.EPlatformName;

/**
 * Holds hardcoded data about all initial games. New ones can be added.
 */
public class GameInfo {

    public final static Set<GameGenrePlatform> games = new HashSet<>() {{

        add(new GameGenrePlatform(
                new Game("Grand Theft Auto: V", 49.99f),
                Set.of(EGenreName.ADVENTURE, EGenreName.ACTION, EGenreName.OPEN_WORLD, EGenreName.ROLE_PLAY, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.PLAYSTATION_5, EPlatformName.PLAYSTATION_4, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("The Legend of Zelda: Breath of the Wild", 59.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ADVENTURE, EGenreName.ACTION, EGenreName.SHOOTER),
                Set.of(EPlatformName.NINTENDO_SWITCH)
        ));

        add(new GameGenrePlatform(
                new Game("Witcher 3: Wild Hunt", 29.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ROLE_PLAY, EGenreName.ADVENTURE),
                Set.of(EPlatformName.PLAYSTATION_5, EPlatformName.PLAYSTATION_4, EPlatformName.XBOX_SERIES_X, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Witcher 3: Wild Hunt Complete Edition", 49.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ROLE_PLAY, EGenreName.ADVENTURE),
                Set.of(EPlatformName.PLAYSTATION_5, EPlatformName.PLAYSTATION_4, EPlatformName.XBOX_SERIES_X, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Need For Speed: Underground 2", 9.99f),
                Set.of(EGenreName.RACING),
                Set.of(EPlatformName.PLAYSTATION_2, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Grand Theft Auto: San Andreas", 19.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ROLE_PLAY, EGenreName.ADVENTURE, EGenreName.ACTION),
                Set.of(EPlatformName.PLAYSTATION_2, EPlatformName.PLAYSTATION_4, EPlatformName.XBOX360, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Red Dead Redemption 2", 39.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ADVENTURE, EGenreName.SHOOTER, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.PLAYSTATION_5, EPlatformName.Personal_Computer, EPlatformName.XBOX_SERIES_X)
        ));

        add(new GameGenrePlatform(
                new Game("Assassin's Creed IV: Black Flag", 29.99f),
                Set.of(EGenreName.ADVENTURE, EGenreName.OPEN_WORLD, EGenreName.ROLE_PLAY),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.XBOX360, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Burnout 3: Takedown", 5.99f),
                Set.of(EGenreName.RACING, EGenreName.ACTION),
                Set.of(EPlatformName.PLAYSTATION_2)
        ));

        add(new GameGenrePlatform(
                new Game("Mortal Kombat 11", 49.99f),
                Set.of(EGenreName.ACTION, EGenreName.FIGHTING, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.NINTENDO_SWITCH, EPlatformName.PLAYSTATION_5, EPlatformName.XBOX_SERIES_X)
        ));

        add(new GameGenrePlatform(
                new Game("Dark Souls", 39.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ADVENTURE, EGenreName.ROLE_PLAY),
                Set.of()
        ));

        add(new GameGenrePlatform(
                new Game("Mario Kart: 8 Deluxe", 29.99f),
                Set.of(EGenreName.RACING, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.NINTENDO_SWITCH)
        ));

        add(new GameGenrePlatform(
                new Game("Call of Duty 4: Modern Warfare", 8.99f),
                Set.of(EGenreName.ACTION, EGenreName.SHOOTER, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.XBOX360, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Rise of the Tomb Raider", 19.99f),
                Set.of(EGenreName.ADVENTURE, EGenreName.ACTION, EGenreName.SHOOTER, EGenreName.OPEN_WORLD),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.XBOX360, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Batman: Arkham City", 12.99f),
                Set.of(EGenreName.ROLE_PLAY, EGenreName.ACTION, EGenreName.OPEN_WORLD),
                Set.of(EPlatformName.NINTENDO_SWITCH, EPlatformName.PLAYSTATION_4, EPlatformName.XBOX360)
        ));

        add(new GameGenrePlatform(
                new Game("Overwatch", 25.99f),
                Set.of(EGenreName.SHOOTER, EGenreName.ACTION, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.NINTENDO_SWITCH)
        ));

        add(new GameGenrePlatform(
                new Game("Counter-Strike 1.6", 3.99f),
                Set.of(EGenreName.SHOOTER, EGenreName.ACTION, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Left 4 Dead 2", 7.99f),
                Set.of(EGenreName.SHOOTER, EGenreName.ACTION, EGenreName.HORROR),
                Set.of(EPlatformName.XBOX360, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("The Elder Scrolls V: Skyrim", 14.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ROLE_PLAY, EGenreName.ADVENTURE),
                Set.of(EPlatformName.NINTENDO_SWITCH, EPlatformName.PLAYSTATION_4, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("The Last of Us: Part 2", 35.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ACTION, EGenreName.SHOOTER, EGenreName.ADVENTURE),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.PLAYSTATION_5)
        ));

        add(new GameGenrePlatform(
                new Game("Red Dead Redemption", 9.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ADVENTURE, EGenreName.SHOOTER),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.XBOX360, EPlatformName.NINTENDO_SWITCH)));

        add(new GameGenrePlatform(
                new Game("Outlast", 11.99f),
                Set.of(EGenreName.HORROR, EGenreName.ACTION),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.NINTENDO_SWITCH, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("God of War: 2", 6.99f),
                Set.of(EGenreName.ADVENTURE, EGenreName.ACTION, EGenreName.ROLE_PLAY),
                Set.of(EPlatformName.PLAYSTATION_2)
        ));

        add(new GameGenrePlatform(
                new Game("Tekken 6", 19.99f),
                Set.of(EGenreName.ACTION, EGenreName.FIGHTING, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.XBOX360)
        ));

        add(new GameGenrePlatform(
                new Game("FIFA 2017", 25.99f),
                Set.of(EGenreName.SPORTS),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.XBOX360)
        ));

        add(new GameGenrePlatform(
                new Game("Grand Theft Auto IV", 19.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ACTION, EGenreName.ROLE_PLAY, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.XBOX360, EPlatformName.PLAYSTATION_4, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Super Mario Kart", 9.99f),
                Set.of(EGenreName.RACING, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.NINTENDO_SWITCH)
        ));

        add(new GameGenrePlatform(
                new Game("Hitman: World of Assassination", 29.99f),
                Set.of(EGenreName.ACTION, EGenreName.SHOOTER, EGenreName.ADVENTURE),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Fortnite", 15.99f),
                Set.of(EGenreName.SHOOTER, EGenreName.ACTION, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.PLAYSTATION_5, EPlatformName.XBOX360, EPlatformName.XBOX_SERIES_X, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(
                new Game("Grand Theft Auto III", 3.99f),
                Set.of(EGenreName.OPEN_WORLD, EGenreName.ACTION, EGenreName.ROLE_PLAY),
                Set.of(EPlatformName.PLAYSTATION_2, EPlatformName.Personal_Computer)
        ));

        add(new GameGenrePlatform(new Game("Uncharted 2: Among Thieves", 18.99f),
                Set.of(EGenreName.ADVENTURE, EGenreName.ACTION, EGenreName.SHOOTER, EGenreName.OPEN_WORLD),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.XBOX360)
        ));

        add(new GameGenrePlatform(
                new Game("The Legend of Zelda: A Link to the Past", 5.99f),
                Set.of(EGenreName.ADVENTURE, EGenreName.ROLE_PLAY, EGenreName.OPEN_WORLD),
                Set.of(EPlatformName.NINTENDO_SWITCH)
        ));


        add(new GameGenrePlatform(
                new Game("Marvel's Spider-Man", 45.99f),
                Set.of(EGenreName.ROLE_PLAY, EGenreName.FIGHTING, EGenreName.OPEN_WORLD),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.XBOX_SERIES_X)
        ));

        add(new GameGenrePlatform(
                new Game("UFC: 4", 39.99f),
                Set.of(EGenreName.FIGHTING, EGenreName.SPORTS, EGenreName.MULTIPLAYER),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.PLAYSTATION_5, EPlatformName.XBOX_SERIES_X, EPlatformName.NINTENDO_SWITCH)
        ));

        add(new GameGenrePlatform(
                new Game("Resident Evil: 4", 42.99f),
                Set.of(EGenreName.HORROR, EGenreName.ACTION),
                Set.of(EPlatformName.PLAYSTATION_4, EPlatformName.Personal_Computer, EPlatformName.NINTENDO_SWITCH)
        ));

    }};

}