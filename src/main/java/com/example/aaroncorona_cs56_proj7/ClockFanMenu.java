package com.example.aaroncorona_cs56_proj7;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
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
        // Border Pane with text area
        BorderPane borderPaneOptionPane = new BorderPane();
        borderPaneOptionPane.setCenter(new TextArea());
        borderPaneOptionPane.setPadding(new Insets(5));

        // Scene
        Scene s = new Scene(borderPaneOptionPane);
        this.setScene(s);
    }
}