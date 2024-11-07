package persistence;

import model.Movie;
import model.MovieLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// modeled on the sample application
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MovieLibrary ml = new MovieLibrary("My Movie Library");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MovieLibrary ml = new MovieLibrary("My Movie Library");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            ml = reader.read();
            assertEquals("My Movie Library", ml.getName());
            assertEquals(0, ml.getMovieList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MovieLibrary ml = new MovieLibrary("My Movie Library");
            ml.addMovie(new Movie("movie1", 98.0, "category1", 7.2));
            ml.addMovie(new Movie("movie2", 120.6, "category2", 8.3));
            ml.getMovieByTitle("movie2").addWatches();
            ml.getMovieByTitle("movie2").addReview("review1");
            ml.getMovieByTitle("movie2").addReview("review2");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            ml = reader.read();
            assertEquals("My Movie Library", ml.getName());
            List<Movie> movies = ml.getMovieList();
            assertEquals(2, movies.size());
            List<String> testReviews = new ArrayList<String>();
            checkMovie("MOVIE1", 98.0, "category1", 7.2, movies.get(0),1, testReviews);
            testReviews = Arrays.asList("review1", "review2");
            checkMovie("MOVIE2", 120.6, "category2", 8.3, movies.get(1), 2, testReviews);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
