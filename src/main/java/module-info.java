module com.example.hotels {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotels to javafx.fxml;
    exports com.example.hotels;
    exports com.example.hotels.controller;
    opens com.example.hotels.controller to javafx.fxml;
    exports com.example.hotels.database;
    opens com.example.hotels.database to javafx.fxml;
}