package com.example.backend.model.genre;

import java.util.Map;
import java.util.HashMap;

public class GenreDescription {

    private final static Map<EGenreName, String> genreDescriptions  = new HashMap<>() {{
        put(EGenreName.SHOOTER, "Dive into high-octane action, wielding an arsenal of powerful firearms to outmaneuver and out shoot opponents in fast-paced environments.");
        put(EGenreName.SPORTS, "Experience the excitement of virtual athleticism, competing in realistic simulations or fantastical settings across a variety of sports disciplines.");
        put(EGenreName.ADVENTURE, "Embark on immersive journeys, solving puzzles, unraveling mysteries, and making critical decisions that shape the narrative in captivating story-driven adventures.");
        put(EGenreName.OPEN_WORLD, "Explore vast, dynamic landscapes with limitless possibilities, where players can roam freely, discover hidden secrets, and shape their own unique gaming experience.");
        put(EGenreName.ACTION, "Engage in adrenaline-fueled gameplay, featuring intense combat, spectacular stunts, and cinematic sequences that keep players on the edge of their seats.");
        put(EGenreName.ROLE_PLAY, "Assume the role of a character in a rich, narrative-driven world, making choices, leveling up, and embarking on epic quests to shape the course of the story.");
        put(EGenreName.RACING, "Hit the track in high-speed vehicles, competing for victory in thrilling races that demand skillful maneuvering and strategic use of power-ups.");
        put(EGenreName.FIGHTING, "Enter the arena to test your combat skills, mastering unique moves and combos as you face off against opponents in intense one-on-one battles.");
        put(EGenreName.HORROR, "Immerse yourself in spine-chilling environments, navigating through dark and eerie atmospheres while facing terrifying creatures or supernatural entities.");
        put(EGenreName.MULTIPLAYER, "Connect with players worldwide for cooperative or competitive gaming experiences, fostering teamwork or engaging in intense battles across various genres.");
    }};

    public static String getDescription(final EGenreName name) {
        return genreDescriptions.get(name);
    }

}