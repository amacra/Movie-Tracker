package persistence;

import org.junit.jupiter.api.Test;

import model.MovieLibrary;
import model.Movie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// modeled on the sample application
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieLibrary ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMovieLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            MovieLibrary ml = reader.read();
            assertEquals("My Movie Library", ml.getName());
            assertEquals(0, ml.getMovieList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMovieLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            MovieLibrary ml = reader.read();
            assertEquals("My Movie Library", ml.getName());
            List<Movie> movies = ml.getMovieList();
            assertEquals(2, movies.size());
            List<String> testReviews = new ArrayList<String>();
            checkMovie("MOVIE1", 98.0, "category1", 7.2, movies.get(0),1, testReviews);
            testReviews = Arrays.asList("review1", "review2");
            checkMovie("MOVIE2", 120.6, "category2", 8.3, movies.get(1), 2, testReviews);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
