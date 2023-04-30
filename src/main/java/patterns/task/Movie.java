package patterns.task;

import patterns.task.MovieClassification.MovieClassification;
import patterns.task.audienceType.AudienceType;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private final String title;
    private final List<String> genres;

    private final List<String> actorSet;

    private final String directorName;

    private final String shortReview;

    private int countOfRent;

    private MovieClassification priceCode;

    private AudienceType audienceType;

    public Movie(String title, List<String> genres, List<String> actorSet, String directorName,
                 String shortReview,int countOfRent, MovieClassification priceCode, AudienceType audienceType) {
        this.title = title;
        this.genres = genres;
        this.actorSet = actorSet;
        this.directorName = directorName;
        this.shortReview = shortReview;
        this.countOfRent = countOfRent;
        this.priceCode = priceCode;
        this.audienceType = audienceType;
    }

    public MovieClassification getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getActorSet() {
        return actorSet;
    }

    public void addCount() {
        countOfRent++;
    }

    public int getCountOfRent() {
        return countOfRent;
    }

    public AudienceType getAudienceType() { return audienceType; }

    public void showInfo() {
        System.out.println("-------------------------------------------");
        System.out.println("Movie title - " + title );
        System.out.print("genres : ");
        genres.forEach(s -> System.out.print(s + ", "));
        System.out.println();
        System.out.print("actors: ");
        actorSet.forEach(s -> System.out.print(s + ", "));
        System.out.println();
        System.out.println("director name - " + directorName);
        System.out.println(shortReview);
        System.out.println("classification - " + priceCode.getClass().getSimpleName());
        System.out.println("auditory - " + audienceType.getClass().getSimpleName());
    }

    public void getStatistics() {
        System.out.println("Film title - " + title);
        System.out.println("Count of rent - " + countOfRent);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genres=" + genres +
                ", actorSet=" + actorSet +
                ", directorName='" + directorName + '\'' +
                ", shortReview='" + shortReview + '\'' +
                ", countOfRent=" + countOfRent +
                ", priceCode=" + priceCode +
                ", audienceType=" + audienceType +
                '}';
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getShortReview() {
        return shortReview;
    }
}