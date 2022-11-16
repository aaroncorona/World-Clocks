module com.example.aaroncorona_cs56_proj7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.aaroncorona_cs56_proj7 to javafx.fxml;
    exports com.example.aaroncorona_cs56_proj7;
}