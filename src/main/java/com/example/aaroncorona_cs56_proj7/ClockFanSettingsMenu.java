package com.example.aaroncorona_cs56_proj7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class that acts as a wrapper for the components needed (labels, text fields, and buttons) to control a Fan
public final class ClockFanSettingsMenu extends JPopupMenu {

    private ClockFan fan;
    private boolean isTimerOn;

    // Components
    private Label startTimeLabel, endTimeLabel, isTimerOnLabel;
    private TextField startTimeTextField, endTimeTextField;
    private Button forceStartButton, forceStopButton, timerToggleButton;

    // Constructor - Each ClockFanSettings obj controls the config of 1 Fan
    public ClockFanSettingsMenu(ClockFan fan) {
        this.fan = fan;

        // Menu settings
        this.setPreferredSize(new Dimension(400, 400));
        this.setVisible(true);

        // Timer should be off by default when the Fan is built
        isTimerOn = false;

        // Add all child components to the Menu
        buildComponents();
    }

    // Helper method to initialize all the components
    private void buildComponents() {
        // Labels
        startTimeLabel = new Label("Start Time (HH:MM:SS):");
        endTimeLabel = new Label("End Time (HH:MM:SS):");
        isTimerOnLabel = new Label("Timer Off");
        // Text fields
        startTimeTextField = new TextField();
        endTimeTextField = new TextField();
        // Buttons
        forceStartButton = new Button("Force Start");
        forceStopButton = new Button("Force Stop");
        timerToggleButton = new Button("Start/Stop Timer");
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
                    isTimerOnLabel.setText("Timer On");
                } else {
                    // Turn off timer
                    fan.stopRotating();
                    isTimerOn = false;
                    // Clear text fields
                    startTimeTextField.setText("");
                    endTimeTextField.setText("");
                    // Update label
                    isTimerOnLabel.setText("Timer Off");
                }
            }
        });
        // Add all components to the Menu
        this.add(startTimeLabel); this.add(endTimeLabel); this.add(isTimerOnLabel);
        this.add(startTimeTextField); this.add(endTimeTextField);
        this.add(forceStartButton); this.add(forceStopButton); this.add(timerToggleButton);
    }

    // Getter methods for all components
    public Label getStartTimeLabel() {
        return startTimeLabel;
    }

    public Label getEndTimeLabel() {
        return endTimeLabel;
    }

    public Label getTimerToggleLabel() {
        return isTimerOnLabel;
    }

    public TextField getStartTimeTextField() {
        return startTimeTextField;
    }

    public TextField getEndTimeTextField() {
        return endTimeTextField;
    }

    public Button getForceStartButton() {
        return forceStartButton;
    }

    public Button getForceStopButton() {
        return forceStopButton;
    }

    public Button getTimerToggleButton() {
        return timerToggleButton;
    }
}
