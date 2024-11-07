package persistence;

import model.Movie;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// modeled on the sample application
public class JsonTest {
    protected void checkMovie(String title, Double duration, String category, Double rating, Movie movie, int watches, List<String> reviews) {
        assertEquals(title, movie.getTitle());
        assertEquals(duration, movie.getDuration());
        assertEquals(category, movie.getCategory());
        assertEquals(rating, movie.getRating());
        assertEquals(watches, movie.getWatches());
        assertEquals(reviews, movie.getReviews());
    }
}
