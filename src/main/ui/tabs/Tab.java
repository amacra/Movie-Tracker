package ui.tabs;

import ui.MovieLibraryUI;

import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {

    private final MovieLibraryUI controller;

    //REQUIRES: MovieLibraryUI controller that holds this tab
    public Tab(MovieLibraryUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the MovieLibraryUI controller for this tab
    public MovieLibraryUI getController() {
        return controller;
    }

}