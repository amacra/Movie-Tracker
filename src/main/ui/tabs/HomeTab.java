package ui.tabs;

import model.Movie;
import model.MovieLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.MovieLibraryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.Event;
import model.EventLog;

//Represents the Home tab
public class HomeTab extends Tab {

    private static final String INIT_GREETING = "Do you want to restore previous list?";
    private static final String JSON_FILE = "./data/movielibrary.json";
    private JLabel greeting;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MovieLibrary movieLibrary;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(MovieLibraryUI controller) {
        super(controller);

        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
        movieLibrary = getController().getMovieLibrary();

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeHomeButtons();
        placeQuitButton();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 2);
        this.add(greeting);
    }

    //EFFECTS: creates Load previous and save current buttons that change greeting message when clicked
    private void placeHomeButtons() {
        JButton b1 = new JButton("Load library");
        JButton b2 = new JButton("Save library");

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(e -> {
            loadMovieList();
            greeting.setText("Loaded previous Movie List!");
        });

        b2.addActionListener(e -> {
            saveMovieList();
            greeting.setText("Saved current Movie List!");
        });

        this.add(buttonRow);
    }

    //EFFECTS: constructs a Quit button that allows user to quit application
    private void placeQuitButton() {
        JPanel moviesBlock = new JPanel();
        JButton moviesButton = new JButton("Quit");
        moviesBlock.add(formatButtonRow(moviesButton));

        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Quit")) {
                    showQuitConfirmation();
                }
            }
        });

        this.add(moviesBlock);
    }

    //EFFECTS: creates a yes/no pop up window asking user if he wants to quit application
    private void showQuitConfirmation() {
        int result = JOptionPane.showConfirmDialog(getController(),
                "Are you sure you want to quit?", "Quit Confirmation", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            for (Event next : EventLog.getInstance()) {
                System.out.println(next.toString());
            }
            System.exit(0);
        }
    }

    // EFFECTS: saves movieList to file
    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieLibrary);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movieList from file
    private void loadMovieList() {
        try {
            for (Movie m : jsonReader.read().getMovieListFromFile()) {
                movieLibrary.addMovieFromFile(m);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE);
        }
    }
}