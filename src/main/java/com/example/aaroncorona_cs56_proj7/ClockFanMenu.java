package com.example.aaroncorona_cs56_proj7;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        Label labelTitle = new Label("Settings for " + fan.getClock());
        Label labelStatus = new Label("Timer is Currently Off");
        Label labelInstructions = new Label("Enter a Start and End Time:");

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

        // 3 Buttons for start timer, force start, force stop
        final Button btnTimer = new Button("Start Timer");
        final Button btnForceStart = new Button("Force Start");
        final Button btnForceStop = new Button("Force Stop");
        final Button btnExit = new Button("Exit");

        // Create a container (vertical box) for all labels, text fields, and buttons
        final VBox root = new VBox();
        root.getChildren().addAll(labelTitle, labelStatus, labelInstructions, tfStart, tfEnd,
                btnTimer, btnForceStart, btnForceStop, btnExit);

        // Scene
        Scene s = new Scene(root);
        this.setScene(s);
    }
}