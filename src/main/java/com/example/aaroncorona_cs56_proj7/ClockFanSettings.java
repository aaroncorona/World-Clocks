package com.example.aaroncorona_cs56_proj7;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class that acts as a wrapper for the components needed (labels, text fields, and buttons) to control a Fan
public final class ClockFanSettings {

    private ClockFan fan;
    private boolean isTimerOn;

    private Label startTimeLabel, endTimeLabel;
    private TextField startTimeTextField, endTimeTextField;
    private Label isTimerOnLabel;


    // Each ClockFanSettings obj controls the config of 1 ClockFan obj
    public ClockFanSettings(ClockFan fan) {
        this.fan = fan;

        // Timer should be off by default when the Fan is built
        isTimerOn = false;

        // Default component UIs (written in order of vertical appearance)
        startTimeLabel = new Label("Start Time (HH:MM:SS):");
        endTimeLabel = new Label("End Time (HH:MM:SS):");
        startTimeTextField = new TextField();
        endTimeTextField = new TextField();
        isTimerOnLabel = new Label("Timer Off");
    }

    // Getter methods for components
    public Label getStartTimeLabel() {
        return startTimeLabel;
    }

    public TextField getStartTimeTextField() {
        return startTimeTextField;
    }

    public Label getEndTimeLabel() {
        return endTimeLabel;
    }

    public TextField getEndTimeTextField() {
        return endTimeTextField;
    }

    public Button getForceStartButton() {
        Button button = new Button("Force Start");
        // Trigger the corresponding Fan to stop
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fan.startRotating();
            }
        });
        return button;
    }

    public Button getForceStopButton() {
        Button button = new Button("Force Stop");
        // Trigger the corresponding Fan to stop
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                fan.stopRotating();
            }
        });
        return button;
    }

    public Button getTimerToggleButton() {
        Button button = new Button("Timer Toggle");
        // Trigger the corresponding Timer to start with the time input in the text boxes, or stop the timer
        button.addMouseListener(new MouseAdapter() {
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
        return button;
    }

    public Label getTimerToggleLabel() {
        return isTimerOnLabel;
    }
}
