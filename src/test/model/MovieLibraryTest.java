package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieLibraryTest {
    private Movie movie1, movie2, movie3;

    private MovieLibrary testMoviesList;

    @BeforeEach
    void runBefore() {
        testMoviesList = new MovieLibrary("my List");
        movie1 = new Movie("movie 1", 108, "category2", 7);
        movie2 = new Movie("movie 2", 97, "category2", 6);
        movie3 = new Movie("movie 2", 97, "category1", 6);
    }

    @Test
    void testConstructor() {
        List<Movie> movies = testMoviesList.getMovieList();
        assertEquals("my List", testMoviesList.getName());
        assertEquals(0, movies.size());
    }

    @Test
    void testAddOneMovie() {
        assertTrue(testMoviesList.addMovie(movie1));
        List<Movie> movies = testMoviesList.getMovieList();
        assertEquals(1, movies.size());
    }

    @Test
    void testAddMultipleMovies() {
        assertTrue(testMoviesList.addMovie(movie1));
        assertTrue(testMoviesList.addMovie(movie2));
        assertFalse(testMoviesList.addMovie(movie3));
        List<Movie> movies = testMoviesList.getMovieList();
        assertEquals(2, movies.size());
    }

    @Test
    void testGetWatchingTimeNoMovies() {
        assertEquals(0, testMoviesList.getWatchTime());
    }

    @Test
    void testGetWatchingTimeMultipleMovies() {
        testMoviesList.addMovie(movie1);
        testMoviesList.addMovie(movie2);
        assertEquals(205, testMoviesList.getWatchTime());
    }

    @Test
    void testGetWatchingTimeWithRewatchedMovies() {
        testMoviesList.addMovie(movie1);
        testMoviesList.addMovie(movie2);
        movie1.addWatches();
        movie2.addWatches();
        assertEquals(410, testMoviesList.getWatchTime());
    }

    @Test
    void testGetMoviesByCategories() {
        testMoviesList.addMovie(movie1);
        testMoviesList.addMovie(movie2);
        List<Movie> movies = testMoviesList.getMoviesByCategory("category2");
        assertEquals(2, movies.size());
        movies = testMoviesList.getMoviesByCategory("no category");
        assertEquals(0, movies.size());
    }

    @Test
    void testGetMoviesByRating() {
        testMoviesList.addMovie(movie1);
        testMoviesList.addMovie(movie2);
        List<Movie> movies = testMoviesList.getMoviesByRating(7);
        assertEquals(1, movies.size());
    }

    @Test
    void testGetFavouritesLessThen10() {
        testMoviesList.addMovie(movie1);
        testMoviesList.addMovie(movie2);
        testMoviesList.addMovie(new Movie("m4", 87, "category1", 8.5));
        List<Movie> movies = testMoviesList.getFavourites();
        assertEquals(3, movies.size());
        assertEquals("M4", movies.get(0).getTitle());
        assertEquals(movie2, movies.get(2));
    }

    @Test
    void testGetFavouritesMoreThen10() {
        testMoviesList.addMovie(movie1);
        testMoviesList.addMovie(movie2);
        testMoviesList.addMovie(new Movie("m4", 87, "category1", 9));
        testMoviesList.addMovie(new Movie("m5", 87, "category1", 8.5));
        testMoviesList.addMovie(new Movie("m6", 87, "category1", 8.5));
        testMoviesList.addMovie(new Movie("m7", 87, "category1", 3));
        testMoviesList.addMovie(new Movie("m8", 87, "category1", 10));
        testMoviesList.addMovie(new Movie("m9", 87, "category1", 8.6));
        testMoviesList.addMovie(new Movie("m10", 87, "category1", 4.5));
        testMoviesList.addMovie(new Movie("m11", 87, "category1", 1));
        testMoviesList.addMovie(new Movie("m12", 87, "category1", 5.5));
        testMoviesList.addMovie(new Movie("m13", 87, "category1", 8.));
        List<Movie> movies = testMoviesList.getFavourites();
        assertEquals(10, movies.size());
        assertEquals("M10", movies.get(9).getTitle());
        assertEquals("M8", movies.get(0).getTitle());
    }

    @Test
    void testGetMostWatched() {
        assertEquals(null, testMoviesList.getMostWatched());
        testMoviesList.addMovie(movie1);
        testMoviesList.addMovie(movie2);
        movie1.addWatches();
        movie2.addWatches();
        assertEquals(movie2, testMoviesList.getMostWatched());
        assertEquals(2, testMoviesList.getMostWatched().getWatches());
        movie1.addWatches();
        assertEquals(movie1, testMoviesList.getMostWatched());
        assertEquals(3, testMoviesList.getMostWatched().getWatches());
    }

    @Test
    void testGetMovieByTitle() {
        testMoviesList.addMovie(movie1);
        assertEquals(movie1, testMoviesList.getMovieByTitle("movie 1"));
        assertEquals(null, testMoviesList.getMovieByTitle("no movie"));
    }

    @Test
    void testHasMovie() {
        testMoviesList.addMovie(movie1);
        assertTrue(testMoviesList.hasMovie(movie1));
        assertFalse(testMoviesList.hasMovie(movie2));
    }
}
