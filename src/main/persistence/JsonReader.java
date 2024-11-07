package persistence;

import model.Event;
import model.EventLog;
import model.Movie;
import model.MovieLibrary;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// modeled on the sample application
// Represents a reader that reads MovieLibrary from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded Movie Library from file"));
        return parseMovieLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private MovieLibrary parseMovieLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MovieLibrary ml = new MovieLibrary(name);
        addMovies(ml, jsonObject);
        return ml;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addMovies(MovieLibrary ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(ml, nextMovie);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses movie from JSON object and adds it to MovieLibrary
    private void addMovie(MovieLibrary ml, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        Double duration = jsonObject.getDouble("duration");
        String category = jsonObject.getString("category");
        Double rating = jsonObject.getDouble("rating");
        int watches = jsonObject.getInt("watches");
        JSONArray reviews = jsonObject.getJSONArray("reviews");
        Movie m = new Movie(title, duration, category, rating);
        m.setWatches(watches);
        for (int i = 0; i < reviews.length(); i++) {
            m.addReview(reviews.getString(i));
        }
        ml.addMovieFromFile(m);
    }
}
