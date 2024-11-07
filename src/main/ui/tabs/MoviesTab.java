package ui.tabs;

import model.Movie;
import ui.MovieLibraryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents the tab where u can see Movies
public class MoviesTab extends Tab {

    private static final String INIT_GREETING = "View your movies!";
    private JLabel greeting;
    private JTextArea outputArea;
    private JScrollPane scrollPane;

    //EFFECTS: constructs a movies tab for console with buttons and a greeting
    public MoviesTab(MovieLibraryUI controller) {
        super(controller);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(new GridLayout(5, 1));

        outputArea = new JTextArea();

        scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(0,0,screenSize.width, 30);
        setVisible(true);

        placeAllMoviesButton();
        placeFavouriteMoviesButton();
        placeMostWatchedMovieButton();
        placeGreeting();
        add(scrollPane, BorderLayout.CENTER);
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 2);
        this.add(greeting);
    }

    //EFFECTS: constructs a movies button that displays all movies and changes greeting
    private void placeAllMoviesButton() {
        JPanel allMoviesBlock = new JPanel();
        JButton allMoviesButton = new JButton("all movies");
        allMoviesBlock.add(formatButtonRow(allMoviesButton));

        allMoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(printList(getController().getMovieLibrary().getMovieList()));
                greeting.setText("All of your movies!");
            }
        });

        this.add(allMoviesBlock);
    }

    //EFFECTS: constructs a movies button that displays favourite movies and changes greeting
    private void placeFavouriteMoviesButton() {
        JPanel favouriteMoviesBlock = new JPanel();
        JButton favouriteMoviesButton = new JButton("favourite movies");
        favouriteMoviesBlock.add(formatButtonRow(favouriteMoviesButton));

        favouriteMoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("favourite movies")) {
                    outputArea.setText(printList(getController().getMovieLibrary().getFavourites()));
                    greeting.setText("All of your favourites!");
                }
            }
        });

        this.add(favouriteMoviesBlock);
    }

    //EFFECTS: prints a list of movies in a sequence of Strings
    private String printList(List<Movie> movieList) {
        String list = "";
        if (movieList.isEmpty()) {
            list = "There are no movies in this list.";
        } else {
            for (Movie m : movieList) {
                list += m.movieToString() + "\n";
            }
        }
        return list;
    }

    //EFFECTS: constructs a movies button that displays favourite movies and changes greeting
    private void placeMostWatchedMovieButton() {
        JPanel mostWatchedMovieBlock = new JPanel();
        JButton mostWatchedMovieButton = new JButton("most watched movie");
        mostWatchedMovieBlock.add(formatButtonRow(mostWatchedMovieButton));

        mostWatchedMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("most watched movie")) {
                    outputArea.setText(getController().getMovieLibrary().getMostWatched().movieToString());
                    greeting.setText("The movie you have wathced most is...");
                }
            }
        });

        this.add(mostWatchedMovieBlock);
    }
}
