package com.example.hotels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SummaryPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PembayaranPage.class.getResource("summary-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Summary Pemesanan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}