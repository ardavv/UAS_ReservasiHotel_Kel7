package com.example.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    @FXML
    public Button pilihExecutiveButton;
    @FXML
    public Button pilihDeluxeButton;
    @FXML
    public Button pilihStandartButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    // Method for navigating to Executive Room page
    @FXML
    private void onExecutiveRoomClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/executive-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Executive Room");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for navigating to Deluxe Room page
    @FXML
    private void onDeluxeRoomClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/deluxe-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Deluxe Room");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for navigating to Standard Room page
    @FXML
    private void onStandardRoomClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/standart-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Standart Room");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // General method to load a room page with its specific title
//    private void loadRoomPage(ActionEvent event, String fxmlFile, String pageTitle) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""));
//            AnchorPane roomPage = fxmlLoader.load();
//
//            // Set up the scene and stage for the new page
//            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(roomPage);
//            stage.setScene(scene);
//            stage.setTitle(pageTitle);
//            stage.show();
//        } catch (IOException e) {
//            System.err.println("Failed to load " + fxmlFile);
//            e.printStackTrace();
//        }
//    }

    // Method to set user details
    public void setUserDetails(String username, String email) {
        if (username != null && !username.isEmpty()) {
            usernameLabel.setText(" " + username + "!");
        } else {
            usernameLabel.setText("Guest!");
        }

        if (email != null && !email.isEmpty()) {
            emailLabel.setText(" " + email);
        } else {
            emailLabel.setText("Not available");
        }
    }


}
