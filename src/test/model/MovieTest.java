package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie movie;

    @BeforeEach
    void runBefore() {
        movie = new Movie("Barbie", 108, "comedy", 7);
    }

    @Test
    void testConstructor() {
        assertEquals("BARBIE", movie.getTitle());
        assertEquals(108, movie.getDuration());
        assertEquals("comedy", movie.getCategory());
        assertEquals(7, movie.getRating());
        assertEquals(1, movie.getWatches());
        assertTrue(movie.getReviews().isEmpty());
    }

    @Test
    void testAddWatches() {
        movie.addWatches();
        movie.addWatches();
        assertEquals(3, movie.getWatches());
    }

    @Test
    void testAddReview() {
        movie.addReview("review 1");
        movie.addReview("review 2");
        assertEquals(2, movie.getReviews().size());
        assertEquals("review 2", movie.getReviews().get(1));
    }

    @Test
    void testMovieToString() {
        assertEquals("Movie title: BARBIE, watched 1 time/s, rating: 7.0, category: comedy", movie.movieToString());
    }
}
