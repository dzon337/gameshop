package com.example.backend.service;

import java.util.*;

import java.time.ZoneOffset;
import java.time.LocalDateTime;

import com.github.javafaker.Faker;

import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;

import com.example.backend.repository.*;
import com.example.backend.model.user.User;
import com.example.backend.model.game.Game;
import com.example.backend.model.order.Order;
import com.example.backend.model.genre.Genre;
import com.example.backend.model.game.GameInfo;
import com.example.backend.model.order.OrderGame;
import com.example.backend.model.genre.EGenreName;
import com.example.backend.model.platform.Platform;
import com.example.backend.model.review.GameReview;
import com.example.backend.model.order.OrderGameKey;
import com.example.backend.model.review.GameReviewKey;
import com.example.backend.model.order.EShippingMethod;
import com.example.backend.model.platform.EPlatformName;
import com.example.backend.model.platform.EManufacturer;
import com.example.backend.model.genre.GenreDescription;
import com.example.backend.exceptions.DatabaseFillerException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseFiller {
    private final static int USER_COUNT = 20;
    private final static int MIN_FRIENDS = 1;
    private final static int MAX_FRIENDS = 3;
    private final static int MIN_REVIEWS = 1;
    private final static int MAX_REVIEWS = 3;
    private final static int MIN_REVIEW_RATING = 1;
    private final static int MAX_REVIEW_RATING = 10;
    private final static int MIN_REVIEW_SENTENCE = 1;
    private final static int MAX_REVIEW_SENTENCE = 3;
    private final static int MIN_USER_ORDERS = 1;
    private final static int MAX_USER_ORDERS = 4;
    private final static int MIN_GAME_QUANTITY = 1;
    private final static int MAX_GAME_QUANTITY = 4;
    private final static List<EShippingMethod> SHIPPING_METHODS = List.of(EShippingMethod.values());
    private final static List<EGenreName> GENRE_TYPES = List.of(EGenreName.values());
    private final static List<EPlatformName> PLATFORM_NAMES = List.of(EPlatformName.values());
    private final static int GENRE_COUNT  = GENRE_TYPES.size();
    private final static int PLATFORM_COUNT = PLATFORM_NAMES.size();
    private final static int SHIPPING_METHOD_COUNT = SHIPPING_METHODS.size();

    private final static Random RANDOM = new Random();
    private final static Faker FAKER = Faker.instance();

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IGameRepository gameRepo;

    @Autowired
    private IGenreRepository genreRepo;

    @Autowired
    private IPlatformRepository platformRepo;

    @Autowired
    private IGameReviewRepository gameReviewRepo;

    @Autowired
    private IOrderRepository orderRepo;

    @Autowired
    private IOrderGameRepository gameOrderRepo;

    public void insertDummyData() {
        try {
            this.deleteData();

            this.generateUsers();
            this.generateFriendships();
            this.generateGenres();
            this.generatePlatforms();
            this.generateGames();
            this.generateGameGenreGamePlatform();
            this.generateGameReviews();
            this.generateOrders();
        }
        catch(Exception e) {
            throw new DatabaseFillerException("Error during database data insertion: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteData() {
        this.gameOrderRepo.deleteAll();
        this.orderRepo.deleteAll();
        this.gameReviewRepo.deleteAll();
        this.detachGamesFromGenresAndPlatforms();
        this.genreRepo.deleteAll();
        this.platformRepo.deleteAll();
        this.gameRepo.deleteAll();
        this.userRepo.deleteAll();
    }

    private void detachGamesFromGenresAndPlatforms() {
        final List<Game> games = this.gameRepo.findAll();

        for (Game game : games) {
            game.setGenres(new HashSet<>());
            game.setPlatforms(new HashSet<>());
        }

        this.gameRepo.saveAll(games);
    }

    private void generateUsers() {
        final Set<User> users = IntStream.
                                    range(0, USER_COUNT)
                                    .mapToObj(userIndex -> User
                                                            .builder()
                                                            .firstname(FAKER.name().firstName())
                                                            .lastname(FAKER.name().lastName())
                                                            .username(RANDOM.nextInt(0,10) +  FAKER.name().username())
                                                            .password(FAKER.internet().password())
                                                            .email(RANDOM.nextInt(0,10) + FAKER.internet().emailAddress())
                                    .build())
                                    .collect(Collectors.toSet());

        this.userRepo.saveAll(users);
    }

    private void generateFriendships() {
        final List<User> allUsers = this.userRepo.findAll();

        for(int userIndex = 0; userIndex < USER_COUNT; ++userIndex) {
            final User currentUser = allUsers.get(userIndex);

            final int userFriendsCount = RANDOM.nextInt(MIN_FRIENDS, MAX_FRIENDS + 1);
            for (int friendCounter = 0; friendCounter < userFriendsCount; ++friendCounter) {
                final Long randomFriendId = RANDOM.nextLong(0, USER_COUNT);

                if (randomFriendId.equals(currentUser.getUser_id())) {
                    continue;
                }

                final boolean alreadyFriends = currentUser
                        .getFriends()
                        .stream()
                        .anyMatch(possibleFriend ->
                                possibleFriend.getUser_id().equals(randomFriendId)
                        );

                if (!alreadyFriends) {
                    final Optional<User> possibleNewFriend = this.userRepo.findById(randomFriendId);
                    possibleNewFriend.ifPresent(currentUser::addFriend);
                }
            }
        }

        this.userRepo.saveAll(allUsers);
    }

    private void generateGenres() {
        final Set<Genre> genres = IntStream
                                    .range(0, GENRE_COUNT)
                                    .mapToObj(GENRE_TYPES::get)
                                    .map(genreType -> Genre
                                                        .builder()
                                                        .description(GenreDescription.getDescription(genreType))
                                                        .genreName(genreType)
                                    .build())
                                    .collect(Collectors.toSet());

        this.genreRepo.saveAll(genres);
    }

    private void generatePlatforms() {
        final Set<Platform> platforms = new HashSet<>();

        IntStream.range(0, PLATFORM_COUNT)
                 .mapToObj(PLATFORM_NAMES::get)
                 .forEach(platformType -> {
                    final EManufacturer manufacturer = switch (platformType) {
                        case PLAYSTATION_2, PLAYSTATION_4, PLAYSTATION_5 -> EManufacturer.SONY;
                        case XBOX360, XBOX_SERIES_X -> EManufacturer.MICROSOFT;
                        case Personal_Computer -> EManufacturer.PC;
                        case NINTENDO_SWITCH -> EManufacturer.NINTENDO;
                    };

                    final Platform platform = Platform
                                                .builder()
                                                .platformName(platformType)
                                                .manufacturer(manufacturer)
                                                .build();
                    platforms.add(platform);
        });

        this.platformRepo.saveAll(platforms);
    }

    private void generateGames() {
        final Set<Game> games = new HashSet<>();

        for (var gameEntry : GameInfo.games) {
            final Game game = Game
                    .builder()
                    .gameName(gameEntry.game().getGameName())
                    .price(gameEntry.game().getPrice())
                    .build();
            games.add(game);
        }

        this.gameRepo.saveAll(games);
    }

    private void generateGameGenreGamePlatform() {
        final List<Game> gamesFromRepo = this.gameRepo.findAll();
        List<Genre> updatedGenres = new ArrayList<>();
        List<Platform> updatedPlatforms = new ArrayList<>();

        for (var gameEntry : GameInfo.games) {
            final String gameName = gameEntry.game().getGameName();
            final Set<EGenreName> gameGenresNames = gameEntry.genres();
            final Set<EPlatformName> gamePlatformsNames = gameEntry.platforms();

            final Optional<Game> possibleGame = gamesFromRepo
                    .stream()
                    .filter(g -> g.getGameName().equals(gameName))
                    .findFirst();

            if (possibleGame.isPresent()) {
                final Set<Genre> gameGenres = new HashSet<>();
                gameGenresNames.forEach(genreName -> {
                    final Optional<Genre> possibleGenre = this.genreRepo.findGenreByGenreName(genreName);
                    possibleGenre.ifPresent(gameGenres::add);
                });

                final Set<Platform> gamePlatforms = new HashSet<>();
                gamePlatformsNames.forEach(platformName -> {
                    final Optional<Platform> possiblePlatform = this.platformRepo.findPlatformByPlatformName(platformName);
                    possiblePlatform.ifPresent(gamePlatforms::add);
                });

                final Game game = possibleGame.get();

                game.addGenres(gameGenres);
                game.addPlatforms(gamePlatforms);

                for (Genre genre : gameGenres) {
                    genre.addGame(game);
                    updatedGenres.add(genre);
                }

                for (Platform platform : gamePlatforms) {
                    platform.addGame(game);
                    updatedPlatforms.add(platform);
                }
            }
        }

        this.gameRepo.saveAll(gamesFromRepo);
        this.genreRepo.saveAll(updatedGenres);
        this.platformRepo.saveAll(updatedPlatforms);
    }

    private void generateGameReviews() {
        final List<User> allUsers = this.userRepo.findAll();
        final List<Game> allGames = this.gameRepo.findAll();
        final int gameCount = allGames.size();

        final List<GameReview> allGameReviews = new ArrayList<>();

        for (int userId = 0; userId < USER_COUNT; ++userId) {
            final User currentUser = allUsers.get(userId);

            final int userReviewCount = RANDOM.nextInt(MIN_REVIEWS, MAX_REVIEWS);
            for (int reviewIndex = 0; reviewIndex < userReviewCount; ++reviewIndex) {
                final int randomGameId = RANDOM.nextInt(0, gameCount);
                final Game game = allGames.get(randomGameId);

                final int rating = RANDOM.nextInt(MIN_REVIEW_RATING, MAX_REVIEW_RATING);
                final int sentenceLength = RANDOM.nextInt(MIN_REVIEW_SENTENCE, MAX_REVIEW_SENTENCE);
                final String text = FAKER.lorem().paragraph(sentenceLength);

                final GameReviewKey compositeKey = GameReviewKey
                        .builder()
                        .reviewId(GameReview.getID())
                        .gameId(game.getGameId())
                        .build();

                final GameReview gameReview = GameReview.builder()
                        .gameReviewId(compositeKey)
                        .reviewText(text)
                        .rating(rating)
                        .build();

                game.addReview(gameReview);
                currentUser.addReview(gameReview);

                gameReview.setUser(currentUser);
                gameReview.setGame(game);

                allGameReviews.add(gameReview);
            }
        }

        this.gameReviewRepo.saveAll(allGameReviews);
        this.userRepo.saveAll(allUsers);
        this.gameRepo.saveAll(allGames);
    }

    private void generateOrders() {
        final List<User> allUsers = this.userRepo.findAll();
        final List<Game> allGames = this.gameRepo.findAll();
        final int gameCount = allGames.size();

        for(int userIndex = 0; userIndex < USER_COUNT; ++userIndex) {
            final User user = allUsers.get(userIndex);
            final int userOrderCount = RANDOM.nextInt(MIN_USER_ORDERS, MAX_USER_ORDERS);

            final int methodIndex = RANDOM.nextInt(0, SHIPPING_METHOD_COUNT);
            final Order order = Order
                    .builder()
                    .orderId(Order.getID())
                    .shippingMethod(SHIPPING_METHODS.get(methodIndex))
                    .order_date(generateRandomDateTime())
                    .user(user)
                    .build();

            user.addOrder(order);

            final Collection<OrderGame> orderedGames = new HashSet<>();

            for(int orderIndex = 0; orderIndex < userOrderCount; ++orderIndex) {
                final int randomGameId = RANDOM.nextInt(0, gameCount);
                final Game game = allGames.get(randomGameId);

                final OrderGameKey orderGameKey = OrderGameKey
                        .builder()
                        .orderId(order.getOrderId())
                        .gameId(game.getGameId())
                        .build();

                final OrderGame orderGame = OrderGame
                        .builder()
                        .orderGameId(orderGameKey)
                        .order(order)
                        .game(game)
                        .quantity(RANDOM.nextInt(MIN_GAME_QUANTITY, MAX_GAME_QUANTITY))
                        .build();

                orderedGames.add(orderGame);
                game.addOrderGames(Set.of(orderGame));
            }
            order.addOrderGames(orderedGames);

            this.orderRepo.save(order);
            this.gameOrderRepo.saveAll(orderedGames);
        }

        this.userRepo.saveAll(allUsers);
        this.gameRepo.saveAll(allGames);
    }

    private LocalDateTime generateRandomDateTime() {
        long startEpochSecond = LocalDateTime.of(2023, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long endEpochSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long randomEpochSecond = ThreadLocalRandom.current().nextLong(startEpochSecond, endEpochSecond);
        return LocalDateTime.ofEpochSecond(randomEpochSecond, 0, ZoneOffset.UTC);
    }

}