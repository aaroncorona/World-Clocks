/***************************************************************************
 Author: Aaron Corona
 Date: Nov 18th, 2022
 CS56 Project #7 - World Clocks
 ***************************************************************************/

package com.example.aaroncorona_cs56_proj7;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private Label lblResult;
    private ScrollBar barRed;
    private ScrollBar barGreen;
    private ScrollBar barBlue;
    private ScrollBar barOpacity;

    // Component settings
    public static final int COMPONENT_WIDTH = 200;
    public static final int COMPONENT_HEIGHT = COMPONENT_WIDTH;

    @Override
    public void start(Stage primaryStage) {
        // Add Clocks on HBox container
        HBox clockRow = new HBox();
        clockRow.setBackground(new Background(new BackgroundFill(Color.YELLOW,null,null)));
        final Clock clock1 = new Clock("PST");
        final Clock clock2 = new Clock("KST");
        final Clock clock3 = new Clock("HST");
        clockRow.getChildren().addAll(clock1, clock2, clock3);

        // Add Fans on HBox container (under the clocks)
        HBox fanRow = new HBox();
        fanRow.setBackground(new Background(new BackgroundFill(Color.PINK,null,null)));
        final ClockFan fan1 = new ClockFan(clock1);
        final ClockFan fan2 = new ClockFan(clock2);
        final ClockFan fan3 = new ClockFan(clock3);
        fanRow.getChildren().addAll(fan1, fan2, fan3);

        // Create a container (vertical box) for the rows of horizontal boxes
        VBox root = new VBox();
        root.getChildren().addAll(clockRow, fanRow);

        // Scene
        Scene scene = new Scene(root, 750,650);
        primaryStage.setTitle("World Clocks");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main method to launch app
    public static void main(String[] args) {
        launch();
    }
}