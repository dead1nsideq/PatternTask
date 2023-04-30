package patterns.task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import patterns.main.Main;
import patterns.task.Adapters.AudienceTypeAdapter;
import patterns.task.Adapters.MovieClassificationAdapter;
import patterns.task.MovieClassification.MovieClassification;
import patterns.task.audienceType.AudienceType;
import patterns.task.factories.AudienceFactory;
import patterns.task.factories.MovieFactory;
import patterns.task.menues.LoginSystem;
import patterns.task.menues.getValuesFromScanner;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MovieLibrary {

    private static HashMap<String,Movie> movies = new HashMap<>();

    public static void searchFilms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Please select search criteria:
                1. Film title
                2. Genre
                3. Actor
                4. Classification
                5. Audience type""");

        int selection = getValuesFromScanner.getIntFromConsole();
        switch (selection) {
            case 1 -> {
                System.out.println("Please enter film title:");
                String title = scanner.nextLine();
                List<Movie> matchingMovies = MovieLibrary.findMoviesByTitle(title);
                displayResults(matchingMovies);
            }
            case 2 -> {
                System.out.println("Please enter genre:");
                String genre = scanner.nextLine();
                List<Movie> matchingMovies = MovieLibrary.findMoviesByGenre(genre);
                displayResults(matchingMovies);
            }
            case 3 -> {
                System.out.println("Please enter actor:");
                String actor = scanner.nextLine();
                List<Movie> matchingMovies = MovieLibrary.findMoviesByActor(actor);
                displayResults(matchingMovies);
            }
            case 4 -> {
                System.out.println("""
                        Please select classification:
                        1. New release
                        2. Regular
                        3. On sale""");
                int classificationSelection = getValuesFromScanner.getIntFromConsole();
                MovieClassification classification = null;
                switch (classificationSelection) {
                    case 1 -> classification = MovieFactory.getNewRelease();
                    case 2 -> classification = MovieFactory.getRegular();
                    case 3 -> classification = MovieFactory.getOnSale();
                }
                List<Movie> matchingMovies = MovieLibrary.findMoviesByClassification(classification);
                displayResults(matchingMovies);
            }
            case 5 -> {
                System.out.println("""
                        Please select audience type:
                        1. Adult
                        2. Teen
                        3. Children""");
                int audienceSelection = getValuesFromScanner.getIntFromConsole();
                AudienceType audienceType = null;
                switch (audienceSelection) {
                    case 1 -> audienceType = AudienceFactory.getAdult();
                    case 2 -> audienceType = AudienceFactory.getTeen();
                    case 3 -> audienceType = AudienceFactory.getChildren();
                }
                List<Movie> matchingMovies = MovieLibrary.findMoviesByAudienceType(audienceType);
                displayResults(matchingMovies);
            }
            default -> System.out.println("Invalid selection.");
        }
    }

    private static List<Movie> findMoviesByGenre(String genre) {
        return movies.values().stream().filter(x -> x.getGenres().contains(genre)).toList();
    }

    private static List<Movie> findMoviesByTitle(String title) {
        return movies.values().stream().filter(x -> x.getTitle().equals(title)).toList();
    }

    private static List<Movie> findMoviesByActor(String actor) {
        return movies.values().stream().filter(x -> x.getActorSet().contains(actor)).toList();
    }

    private static List<Movie> findMoviesByClassification(MovieClassification classification) {
        return movies.values().stream().filter(x -> x.getPriceCode().equals(classification)).toList();
    }

    private static List<Movie> findMoviesByAudienceType(AudienceType audienceType) {
        return movies.values().stream().filter(x -> x.getAudienceType().equals(audienceType)).toList();
    }

    private static void displayResults(List<Movie> matchingMovies) {
        int count = matchingMovies.size();
        System.out.println("Results: " + count + " films found");
        if (count == 0) {
            System.out.println("No films match your query.");
        } else {
            for (Movie m : matchingMovies) {
                m.showInfo();
            }
        }
    }

    public static void pickUpFilms(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        showAllFilms();
        String title;
        do {
            System.out.println("Please enter the title of the film you want to add to your shopping cart:");
            title = scanner.nextLine();
        } while (!isValidFilmTitle(title) && isFilmInShoppingCart(title,customer));

        int rentDays;
        do {
            System.out.println("Please enter the number of days you want to rent the film (form 1 to 10):");
            rentDays = getValuesFromScanner.getIntFromConsole();
        } while (rentDays < 1 || rentDays > 10);

        Movie selectedMovie = movies.get(title);
        customer.addToShoppingCart(new Rental(selectedMovie, rentDays));
        LoginSystem.writeToFileCustomers();
    }

    private static boolean isFilmInShoppingCart(String title,Customer customer) {
        for (Rental rental: customer.getShoppingCart()) {
            if (rental.getMovie().getTitle().equals(title)) {
                System.out.println("This film are already in your shopping cart");
                return false;
            }
        }
        return true;
    }

    private static boolean isValidFilmTitle(String title) {
        if (!movies.containsKey(title)) {
            System.out.println("Invalid film title. Please try again.");
            return false;
        }
        return true;
    }

    public static void showAllFilms() {
        for (Movie movie: movies.values()
        ) {
            movie.showInfo();
        }
    }

    public static void writeMoviesToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(MovieClassification.class, new MovieClassificationAdapter())
                .registerTypeAdapter(AudienceType.class, new AudienceTypeAdapter())
                .create();
        String json = gson.toJson(movies);
        try {
        FileWriter writer = new FileWriter("movies.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readMoviesFromFile() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MovieClassification.class, new MovieClassificationAdapter())
                .registerTypeAdapter(AudienceType.class, new AudienceTypeAdapter())
                .create();
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("movies.json")));
            Type type = new TypeToken<HashMap<String, Movie>>(){}.getType();
            movies = gson.fromJson(jsonContent, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showFilmsStatistics() {
        for (Movie movie: movies.values()) {
            movie.getStatistics();
        }
    }

    public static void makeMovie() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter film name");
        String filmName = scanner.nextLine();

        List<String> genres;
        do {
            System.out.println("Please enter genres separated by commas");
            genres = Arrays.stream(scanner.nextLine().split(","))
                    .map(String::trim)
                    .filter(genre -> !genre.isEmpty())
                    .collect(Collectors.toList());
        } while (genres.isEmpty());

        List<String> actors;
        do {
            System.out.println("Please enter actors separated by commas");
            actors = Arrays.stream(scanner.nextLine().split(","))
                    .map(String::trim)
                    .filter(actor -> !actor.isEmpty())
                    .collect(Collectors.toList());
        } while (actors.isEmpty());

        System.out.println("Please enter director name");
        String directorName = scanner.nextLine();

        System.out.println("Please enter short review");
        String shortReview = scanner.nextLine();

        MovieClassification movieType;
        do {
            System.out.println("""
                    Please enter film classification:
                    1. New release
                    2. Regular
                    3. On sale""");
            int n = getValuesFromScanner.getIntFromConsole();
            switch (n) {
                case 1 -> movieType = MovieFactory.getNewRelease();
                case 2 -> movieType = MovieFactory.getRegular();
                case 3 -> movieType = MovieFactory.getOnSale();
                default -> {
                    System.out.println("Invalid input. Please enter a valid option.");
                    movieType = null;
                }
            }
        } while (movieType == null);

        AudienceType audienceType;
        do {
            System.out.println("""
                    Please enter film audience type:
                    1. Adult
                    2. Teen
                    3. Children""");
            int n = getValuesFromScanner.getIntFromConsole();
            switch (n) {
                case 1 -> audienceType = AudienceFactory.getAdult();
                case 2 -> audienceType = AudienceFactory.getTeen();
                case 3 -> audienceType = AudienceFactory.getChildren();
                default -> {
                    System.out.println("Invalid input. Please enter a valid option.");
                    audienceType = null;
                }
            }
        } while (audienceType == null);

        Movie movie = new Movie(filmName, genres, actors, directorName, shortReview,0, movieType, audienceType);
        movies.put(filmName,movie);
        MovieLibrary.writeMoviesToFile();
    }

    public static void addCountToMovie(Movie movie) {
        movie.addCount();
    }

    public static List<Movie> getMovies() {
        return movies.values().stream().toList();
    }
}
