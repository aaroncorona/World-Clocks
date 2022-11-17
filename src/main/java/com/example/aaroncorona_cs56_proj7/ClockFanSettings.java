package com.example.aaroncorona_cs56_proj7;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class that acts as a wrapper for the components needed (labels, text fields, and buttons) to control a Fan
public final class ClockFanSettings {

    private ClockFan fan;
    private boolean isTimerOn;

    // Components
    private Label startTimeLabel, endTimeLabel, isTimerOnLabel;
    private TextField startTimeTextField, endTimeTextField;
    private Button forceStartButton, forceStopButton, timerToggleButton;

    // Constructor - Each ClockFanSettings obj controls the config of 1 Fan
    public ClockFanSettings(ClockFan fan) {
        this.fan = fan;

        // Timer should be off by default when the Fan is built
        isTimerOn = false;

        // Default Label UIs
        startTimeLabel = new Label("Start Time (HH:MM:SS):");
        endTimeLabel = new Label("End Time (HH:MM:SS):");
        isTimerOnLabel = new Label("Timer Off");
        // Default Text fields
        startTimeTextField = new TextField();
        endTimeTextField = new TextField();
        // Default Buttons
        forceStartButton  = new Button("Force Start");
        forceStopButton = new Button("Force Stop");
        timerToggleButton = new Button("Start/Stop Timer");
    }

    // Getter methods for components
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
        // Trigger the corresponding Fan to stop
        forceStartButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fan.startRotating();
            }
        });
        return forceStartButton;
    }

    public Button getForceStopButton() {
        // Trigger the corresponding Fan to stop
        forceStopButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fan.stopRotating();
            }
        });
        return forceStopButton;
    }

    public Button getTimerToggleButton() {
        // Trigger the corresponding Timer to start with the time input in the text boxes, or stop the timer
        timerToggleButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(isTimerOn == false) {
                    // Turn on timer
                    String startTime = startTimeTextField.getText().substring(0,6);
                    String endTime = endTimeTextField.getText().substring(0,6);
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
        return timerToggleButton;
    }
}
