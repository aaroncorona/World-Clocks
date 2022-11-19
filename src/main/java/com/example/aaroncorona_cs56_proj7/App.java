/***************************************************************************
 Author: Aaron Corona
 Date: Nov 18th, 2022
 CS56 Project #7 - World Clocks
 ***************************************************************************/

package com.example.aaroncorona_cs56_proj7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    // Component settings
    public static final int COMPONENT_WIDTH = 200;
    public static final int COMPONENT_HEIGHT = COMPONENT_WIDTH;

    @Override
    public void start(Stage primaryStage) {
        // Add Clocks on an HBox container
        HBox clockRow = new HBox();
        clockRow.setBackground(new Background(new BackgroundFill(Color.YELLOW,null,null)));
        final Clock clock1 = new Clock("PST");
        final Clock clock2 = new Clock("KST");
        final Clock clock3 = new Clock("HST");
        clockRow.getChildren().addAll(clock1, clock2, clock3);

        // Add Fans on an HBox container
        HBox fanRow = new HBox();
        fanRow.setBackground(new Background(new BackgroundFill(Color.PINK,null,null)));
        final ClockFan fan1 = new ClockFan(clock1);
        final ClockFan fan2 = new ClockFan(clock2);
        final ClockFan fan3 = new ClockFan(clock3);
        fanRow.getChildren().addAll(fan1, fan2, fan3);

        // Add Menus for Fan settings (to be opened by buttons)
        ClockFanMenu fan1menu = new ClockFanMenu(fan1);
        ClockFanMenu fan2menu = new ClockFanMenu(fan2);
        ClockFanMenu fan3menu = new ClockFanMenu(fan3);

        // Add Buttons to open the Menus on an HBox container
        HBox buttonRow = new HBox();
        buttonRow.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        Button button1 = new Button("Fan 1 Settings");
        button1.setOnAction(event -> {
            if(fan1menu.isShowing()) {
                fan1menu.hide();
            }
            fan1menu.show();
        });
        Button button2 = new Button("Fan 2 Settings");
        button2.setOnAction(event -> {
            if(fan2menu.isShowing()) {
                fan2menu.hide();
            }
            fan2menu.show();
        });
        Button button3 = new Button("Fan 3 Settings");
        button3.setOnAction(event -> {
            if(fan3menu.isShowing()) {
                fan3menu.hide();
            }
            fan3menu.show();
        });
        buttonRow.getChildren().addAll(button1, button2, button3);

        // Create a container (vertical box) for the rows of horizontal boxes
        VBox root = new VBox();
        root.getChildren().addAll(clockRow, fanRow, buttonRow);

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