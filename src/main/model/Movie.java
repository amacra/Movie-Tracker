package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a Movie Object
public class Movie implements Comparable<Movie> {

    private String title;
    private double duration;
    private String category;
    private double rating;
    private List<String> reviews;
    private int watches;

    //REQUIRES: 0<= rating <=10, duration >0
    //MODIFIES: this
    //EFFECTS: creates a Movie object with a title, duration (in minutes), category and rating
    public Movie(String title, double duration, String category, double rating) {
        this.title = title.toUpperCase();
        this.duration = duration;
        this.category = category;
        this.rating = rating;
        this.reviews = new ArrayList<String>();
        this.watches = 1;
    }

    public int getWatches() {
        return watches;
    }

    public void addWatches() {
        this.watches += 1;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public String getTitle() {
        return title.toUpperCase();
    }

    public double getDuration() {
        return duration;
    }

    public String getCategory() {
        return category;
    }

    public void setWatches(int watches) {
        this.watches = watches;
    }

    public double getRating() {
        return rating;
    }

//    @Override
//    public int compareTo(Movie movie) {
//        return (int) (movie.rating - this.rating);
//    }

    public int compareTo(Movie movie) {
        return Double.compare(movie.rating, this.rating);
    }

    //EFFECTS: returns movie as a String
    public String movieToString() {
        return "Movie title: " + title + ", watched " + watches + " time/s, rating: "
                + rating + ", category: " + category;
    }

    //EFFECTS: returns movie as JSON objects
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("duration", duration);
        json.put("category", category);
        json.put("rating", rating);
        json.put("watches", watches);
        json.put("reviews", reviewsToJson());
        return json;
    }

    // EFFECTS: returns reviews as a JSON array
    private JSONArray reviewsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : reviews) {
            jsonArray.put(s);
        }

        return jsonArray;
    }
}
