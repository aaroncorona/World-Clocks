package com.example.aaroncorona_cs56_proj7;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClockFanMenu extends Stage {

    ClockFan fan;

    public ClockFanMenu(ClockFan fan) {
        super();
        this.fan = fan;

        this.initModality(Modality.APPLICATION_MODAL);

        buildComponents();
    }

    private void buildComponents() {
        // Label for the title, status, and instructions
        final Label labelTitle = new Label("Settings for " + fan.getClock());
        Label labelStatus = new Label("Status: Timer is OFF");
        final Label labelInstructions = new Label("Enter a Start and End Time:");

        // Text fields for start and end time
        final TextField tfStart = new TextField();
        tfStart.setPromptText("Start Time (HH:MM:SS):");
        tfStart.setPrefColumnCount(6);
        // End time text field
        final TextField tfEnd = new TextField();
        tfEnd.setPromptText("End Time (HH:MM:SS):");
        tfEnd.setPrefColumnCount(6);
        final VBox tfColumn = new VBox();
        tfColumn.getChildren().addAll(tfStart, tfEnd);

        // 4 Buttons for start timer, force start, force stop
        final Button btnTimer = new Button("Start Timer");
        final Button btnForceStart = new Button("Force Start");
        final Button btnForceStop = new Button("Force Stop");
        final Button btnExit = new Button("Exit");

        // Button action listeners
        btnTimer.setOnAction(event -> {
            // Start the Fan timer with the inputted range and update the status label
            String startTime = tfStart.getText();
            String endTime = tfEnd.getText();
            // Check for valid input
            if(startTime.length() != 6
               || endTime.length() != 6) {
                System.out.println("Invalid Time Entered");
            } else {
                fan.startRotating(startTime, endTime);
                labelStatus.setText("Status: Timer is ON");
            }
        });
        btnForceStart.setOnAction(event -> {
            // Start the Fan
            fan.startRotating();
        });
        btnForceStop.setOnAction(event -> {
            // Stop the Fan
            fan.stopRotating();
            labelStatus.setText("Status: Timer is OFF");
        });
        btnExit.setOnAction(event -> {
            // Close the stage
            hide();
        });

        // Create a container (vertical box) for all labels, text fields, and buttons
        final VBox vBox = new VBox();
        vBox.getChildren().addAll(labelTitle, labelStatus, labelInstructions, tfStart, tfEnd,
                btnTimer, btnForceStart, btnForceStop, btnExit);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(15, 15, 15, 15));

        // Scene
        Scene s = new Scene(vBox);
        this.setScene(s);
    }
}