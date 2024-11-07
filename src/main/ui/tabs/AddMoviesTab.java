package ui.tabs;

import model.Movie;
import ui.MovieLibraryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the AddMovies tab
public class AddMoviesTab extends Tab {

    private JTextField titleField;
    private JTextField durationField;
    private JTextField categoryField;
    private JTextField ratingField;
    private JTextArea outputArea;
    private static final String INIT_GREETING = "Add a movie to your library!";
    private JLabel greeting;
    private JScrollPane scrollPane;

    //EFFECTS: constructs a movies tab for console with buttons and a greeting
    public AddMoviesTab(MovieLibraryUI controller) {
        super(controller);

        setLayout(new GridLayout(7, 2));

        titleField = new JTextField();
        durationField = new JTextField();
        categoryField = new JTextField();
        ratingField = new JTextField();
        outputArea = new JTextArea();

        scrollPane = new JScrollPane(outputArea);
        setVisible(true);

        placeGreeting();
        add(new JLabel(""));

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Duration:"));
        add(durationField);
        add(new JLabel("Category:"));
        add(categoryField);
        add(new JLabel("Rating (1-10):"));
        add(ratingField);

        placeAddMovieButton();
        add(scrollPane);
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 2);
        this.add(greeting);
    }

    //EFFECTS: constructs a movies button that displays all movies
    private void placeAddMovieButton() {
        JPanel addMovieBlock = new JPanel();
        JButton addMovieButton = new JButton("add movie");
        addMovieBlock.add(formatButtonRow(addMovieButton));

        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("add movie")) {
                    addMovie();
                }
            }
        });
        this.add(addMovieBlock);
    }

    //REQUIRES: 0<=rating<=10 and duration>0
    //EFFECTS: adds movie to list from the input data in panels
    private void addMovie() {
        String title = titleField.getText();
        Double duration = Double.parseDouble(durationField.getText());
        String category = categoryField.getText();
        Double rating = Double.parseDouble(ratingField.getText());

        if (duration < 0) {
            outputArea.setText("Duration has to be positive");
            greeting.setText("Can't add movie!");
        } else if (rating <= 0 || rating >= 10) {
            outputArea.setText("Rating must be in between 1-10");
            greeting.setText("Can't add movie!");
        } else {
            Movie movie = new Movie(title, duration, category, rating);
            if (getController().getMovieLibrary().hasMovie(movie)) {
                getController().getMovieLibrary().getMovieByTitle(title).addWatches();
            } else {
                getController().getMovieLibrary().addMovie(movie);
            }
            outputArea.setText(getController().getMovieLibrary().getMovieByTitle(title).movieToString());
            greeting.setText("Added movie successfully!");
        }
    }
}
