package com.example.aaroncorona_cs56_proj7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class to create a JPopupMenu for the components needed (labels, text fields, and buttons) to control a Fan
public final class ClockFanMenu extends JPopupMenu {

    private ClockFan fan;
    private boolean isTimerOn;

    // Components
    private Label startTimeLabel, endTimeLabel, isTimerOnLabel;
    private TextField startTimeTextField, endTimeTextField;
    private Button forceStartButton, forceStopButton, timerToggleButton, exitButton;

    // Constructor - Each ClockFanSettings obj controls the config of 1 Fan
    public ClockFanMenu(ClockFan fan) {
        this.fan = fan;
        setBackground(Color.LIGHT_GRAY);
//        this.setLayout(new GridLayout());

        // Menu settings
        setPreferredSize(new Dimension(400, 400));
        setLocation(fan.getXCenter(), fan.getYCenter());

        // Timer should be off by default when the Fan is built
        isTimerOn = false;

        // Add all child components to the Menu
        buildComponents();
    }

    // Helper method to initialize all the components
    private void buildComponents() {
        // Labels
        isTimerOnLabel = new Label("Timer is Currently Off");
        startTimeLabel = new Label("Start Time (HH:MM:SS):");
        endTimeLabel = new Label("End Time (HH:MM:SS):");
        // Text fields
        startTimeTextField = new TextField();
        endTimeTextField = new TextField();
        // Buttons
        forceStartButton = new Button("Force Start");
        forceStopButton = new Button("Force Stop");
        timerToggleButton = new Button("Start/Stop Timer");
        exitButton = new Button("Exit");
        // Button Listeners
        // Force Start Button - Trigger the corresponding Fan to start
        forceStartButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fan.startRotating();
            }
        });
        // Force Stop Button - Trigger the corresponding Fan to stop
        forceStopButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fan.stopRotating();
            }
        });
        // Timer Toggle button
        timerToggleButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isTimerOn == false) {
                    // Turn on timer
                    String startTime = startTimeTextField.getText().substring(0, 6);
                    String endTime = endTimeTextField.getText().substring(0, 6);
                    fan.startRotating(startTime, endTime);
                    isTimerOn = true;
                    // Update label
                    isTimerOnLabel.setText("Timer is Currently On");
                } else {
                    // Turn off timer
                    fan.stopRotating();
                    isTimerOn = false;
                    // Clear text fields
                    startTimeTextField.setText("");
                    endTimeTextField.setText("");
                    // Update label
                    isTimerOnLabel.setText("Timer is Currently Off");
                }
            }
        });
        // Exit Menu button
        exitButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
            }
        });
        // Add all components to the Menu
        this.add(isTimerOnLabel);
        this.add(startTimeLabel); this.add(startTimeTextField);
        this.add(endTimeLabel); this.add(endTimeTextField);
        this.add(forceStartButton); this.add(forceStopButton); this.add(timerToggleButton); this.add(exitButton);
    }

    // Open or closes the menu
    public void toggleMenu(){
        if(this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }
}
