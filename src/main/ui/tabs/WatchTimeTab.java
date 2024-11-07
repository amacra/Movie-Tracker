package ui.tabs;

import ui.MovieLibraryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the tab where u can see the watch time and image of your watch time age
public class WatchTimeTab extends Tab {

    private static final String INIT_GREETING = "What age would you be if all you did was watch movies?";
    private JLabel greeting;
    private JTextArea outputArea;

    //EFFECTS: constructs a movies tab for console with buttons and a greeting
    public WatchTimeTab(MovieLibraryUI controller) {
        super(controller);
        outputArea = new JTextArea();
        setLayout(new GridLayout(5, 1));
        placeGreeting();
        placeWatchTimeButton();
        add(outputArea);
        placeAgeWatchTimeButton();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 2);
        this.add(greeting);
    }

    //EFFECTS: creates a watchTime button that displays the watch time in days hours and minutes
    private void placeWatchTimeButton() {
        JPanel watchTimeBlock = new JPanel();
        JButton watchTimeButton = new JButton("watch time");
        watchTimeBlock.add(formatButtonRow(watchTimeButton));

        watchTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double time = getController().getMovieLibrary().getWatchTime();
                int days = (int) time / (60 * 24);
                int hours = (int) (time - (days * 24 * 60)) / 60;
                double minutes = time - (hours * 60) - (days * 60 * 24);
                outputArea.setText("days: " + days + ", hours: " + hours + ", minutes: " + minutes);
            }
        });

        this.add(watchTimeBlock);
    }

    //EFFECTS: creates AgeWatchTime button that will pop up an image
    private void placeAgeWatchTimeButton() {
        JPanel ageWatchTimeBlock = new JPanel();
        JButton ageWatchTimeButton = new JButton("watch time Age");
        ageWatchTimeBlock.add(formatButtonRow(ageWatchTimeButton));

        ageWatchTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ageBasedOnWatchTime();
            }
        });

        this.add(ageWatchTimeBlock);
    }

    //EFFECTS: pops up an image of people with the age one would be if all they did was watch movies from the list
    private void ageBasedOnWatchTime() {
        JFrame imageFrame = new JFrame();
        imageFrame.setSize(300, 300);
        ImageIcon icon;
        double age = getController().getMovieLibrary().getWatchTime() / (60 * 24 * 365);
        if (age < 2) {
            icon = new ImageIcon("./data/baby.jpg");
        } else if (age < 10) {
            icon = new ImageIcon("./data/kids.jpg");
        } else if (age < 18) {
            icon = new ImageIcon("./data/teenagers.jpg");
        } else if (age < 45) {
            icon = new ImageIcon("./data/adults.jpg");
        } else {
            icon = new ImageIcon("./data/elderly.jpg");
        }
        JLabel imageLabel = new JLabel(scaleImage(icon, 300, 300));
        imageFrame.add(imageLabel);
        imageFrame.setVisible(true);
    }

    //EFFECTS: scales images
    public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if (icon.getIconWidth() > w) {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if (nh > h) {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }
        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
}
