package nikola.bottomnavigationview.Model;

/**
 * Created by Nikola on 4/27/2017.
 */

public class Film {

    private String title;
    private String description;
    private String poster;
    private String rate;
    private String movieId;


    public Film(String movieId, String title, String description, String poster, String rate) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.rate = rate;
    }

    public Film(String title, String description, String poster, String rate) {
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.rate = rate;
    }

    public Film(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Film(String original_title, String overview, String poster_path) {
        this.title       = original_title;
        this.description = overview;
        this.poster      = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public String getRate() {
        return rate;
    }

    public String getTrailerId() {
        return movieId;
    }

    public void setTrailerId(String movieId) {
        this.movieId = movieId;
    }
}
