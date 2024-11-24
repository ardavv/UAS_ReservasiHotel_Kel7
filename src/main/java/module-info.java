module com.example.hotels {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotels to javafx.fxml;
    exports com.example.hotels;
}