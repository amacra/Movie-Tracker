package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a List of Movie objects
public class MovieLibrary implements Writable {

    private String name;
    private List<Movie> movieList;

    // EFFECTS: creates an empty new movie library
    public MovieLibrary(String name) {
        this.name = name;
        movieList = new ArrayList<Movie>(0);
    }

    //MODIFIES: this
    //EFFECTS: adds movie to list only if it is not already there and returns true,
    // if movie with the same title is already in the list returns false
    public boolean addMovie(Movie movie) {
        int hasMovie = 0;
        for (Movie m : movieList) {
            if (m.getTitle().equals(movie.getTitle())) {
                hasMovie = 1;
            }
        }
        if (hasMovie > 0) {
            return false;
        } else {
            movieList.add(movie);
            EventLog.getInstance().logEvent(new Event("Movie added to list: " + movie.getTitle()));
            return true;
        }
    }

    //EFFECTS: returns total time spent on watching the movies in the library
    public double getWatchTime() {
        double time = 0;
        for (Movie m : movieList) {
            if (m.getWatches() > 1) {
                time += m.getDuration() * m.getWatches();
            } else {
                time += m.getDuration();
            }
        }
        EventLog.getInstance().logEvent(new Event("Total time watching requested: " + time));
        return time;
    }

    //EFFECTS: return title of movie that was watched most times (in case where equally watched most recent add)
    public Movie getMostWatched() {
        if (movieList.isEmpty()) {
            return null;
        }
        Movie mostWatched = movieList.get(0);
        for (int i = 1; i < movieList.size(); i++) {
            if (movieList.get(i).getWatches() >= mostWatched.getWatches()) {
                mostWatched = movieList.get(i);
            }
        }
        EventLog.getInstance().logEvent(new Event("Most watched movie requested: " + mostWatched.getTitle()));
        return mostWatched;
    }

    //EFFECTS: returns the Movie List in order in which they were added
    public List<Movie> getMovieList() {
        EventLog.getInstance().logEvent(new Event("Requested list of all movies: returned list of size "
                + movieList.size()));
        return movieList;
    }

    //EFFECTS: returns a list of movies from the Library with the asked for category
    public List<Movie> getMoviesByCategory(String category) {
        List<Movie> movies = new ArrayList<Movie>();
        for (Movie m : movieList) {
            if (m.getCategory().equals(category)) {
                movies.add(m);
            }
        }
        EventLog.getInstance().logEvent(new Event("Requested list of movies of category: "
                + category + ", returned list of size " + movies.size()));
        return movies;
    }

    //REQUIRES: 10 > rating > 0
    //EFFECTS: returns a list of movies from our MovieLibrary that have an equal or higher rating then called
    public List<Movie> getMoviesByRating(double rating) {
        List<Movie> movies = new ArrayList<Movie>();
        for (Movie m : movieList) {
            if (m.getRating() >= rating) {
                movies.add(m);
            }
        }
        EventLog.getInstance().logEvent(new Event("Requested list of movies of rating: "
                + rating + ", returned list of size " + movies.size()));
        return movies;
    }

    //EFFECTS: returns a sorted list of up to 10 movies with the highest rating
    public List<Movie> getFavourites() {
        List<Movie> movies = new ArrayList<Movie>();
        for (Movie m : movieList) {
            movies.add(m);
        }
        Collections.sort(movies);
        if (movies.size() > 10) {
            return movies.subList(0, 10);
        } else {
            EventLog.getInstance().logEvent(new Event("Requested list of favourites: returned list of size "
                    + movies.size()));
            return movies;
        }
    }

    //EFFECTS: returns movie object of a specific title
    public Movie getMovieByTitle(String title) {
        Movie movie = null;
        for (Movie m : movieList) {
            if (m.getTitle().equals(title.toUpperCase())) {
                movie = m;
            }
        }
        return movie;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("movies", movieListToJson());
        return json;
    }

    // EFFECTS: returns MovieList as a JSON array
    private JSONArray movieListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : movieList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: returns true if movie is already in list and false otherwise
    public boolean hasMovie(Movie movie) {
        boolean hasMovie = false;
        for (Movie m : movieList) {
            if (m.getTitle().equals(movie.getTitle())) {
                hasMovie = true;
            }
        }
        return hasMovie;
    }

    //EFFECTS: adds movie when reading from file
    public void addMovieFromFile(Movie movie) {
        int hasMovie = 0;
        for (Movie m : movieList) {
            if (m.getTitle().equals(movie.getTitle())) {
                hasMovie = 1;
            }
        }
        if (!(hasMovie > 0)) {
            movieList.add(movie);
        }
    }

    public List<Movie> getMovieListFromFile() {
        return movieList;
    }
}
