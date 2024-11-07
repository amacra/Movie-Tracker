package ui;

import model.MovieLibrary;

import javax.swing.*;
import ui.tabs.HomeTab;
import ui.tabs.MoviesTab;
import ui.tabs.AddMoviesTab;
import ui.tabs.WatchTimeTab;

//Represents the User Interface for the Movie Library app
public class MovieLibraryUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int MOVIES_TAB_INDEX = 1;
    public static final int ADDMOVIES_TAB_INDEX = 2;
    public static final int WATCHTIME_TAB_INDEX = 3;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;
    private MovieLibrary movieLibrary;

    public static void main(String[] args) {
        new MovieLibraryUI();
    }

    //MODIFIES: this
    //EFFECTS: creates MovieLibraryUI, displays sidebar and tabs
    private MovieLibraryUI() {
        super("Movie Library");
        setSize(WIDTH, HEIGHT);

        movieLibrary = new MovieLibrary("My Movie Library");

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);

    }

    //EFFECTS: returns MovieLibrary object controlled by this UI
    public MovieLibrary getMovieLibrary() {
        return movieLibrary;
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, movies tab, add movie tab and watch time tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel moviesTab = new MoviesTab(this);
        JPanel addMoviesTab = new AddMoviesTab(this);
        JPanel watchTimeTab = new WatchTimeTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(moviesTab, MOVIES_TAB_INDEX);
        sidebar.setTitleAt(MOVIES_TAB_INDEX, "Movies");
        sidebar.add(addMoviesTab, ADDMOVIES_TAB_INDEX);
        sidebar.setTitleAt(ADDMOVIES_TAB_INDEX, "Add Movies");
        sidebar.add(watchTimeTab, WATCHTIME_TAB_INDEX);
        sidebar.setTitleAt(WATCHTIME_TAB_INDEX, "Watch Time");

    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }
}