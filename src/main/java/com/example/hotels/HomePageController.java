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
import java.util.ArrayList;
import java.util.List;

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

    private List<Room> rooms;

    // Initialize the list of rooms
    public void initialize() {
        rooms = new ArrayList<>();
        rooms.add(new Room("pilihExecutiveButton", "Executive Room", "/image/executive.png",
                "Kamar mewah dengan ruang tamu terpisah, fasilitas eksklusif, dan kenyamanan maksimal bagi tamu istimewa.",
                "/com/example/hotels/executive-view.fxml", 600000)); // harga 600rb
        rooms.add(new Room("pilihDeluxeButton", "Deluxe Room", "/image/deluxe.png",
                "Kamar mewah dengan ruang tamu terpisah, fasilitas eksklusif, dan kenyamanan maksimal bagi tamu istimewa.",
                "/com/example/hotels/deluxe-view.fxml", 400000)); // harga 400rb
        rooms.add(new Room("pilihStandartButton", "Standard Room", "/image/standard.png",
                "Kamar standar dengan kenyamanan yang memadai dan harga yang lebih terjangkau.",
                "/com/example/hotels/standart-view.fxml", 200000)); // harga 200rb
    }

    // General method to load a room page dynamically
    private void loadRoomPage(ActionEvent event, Room room) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(room.getFxmlFile()));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(room.getName());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for navigating to the selected room page
    @FXML
    private void onRoomClick(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        Room selectedRoom = null;

        // Find the room based on the button's fx:id
        for (Room room : rooms) {
            if (room.getId().equalsIgnoreCase(sourceButton.getId())) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            loadRoomPage(event, selectedRoom);
        }
    }

    // Method to set user details
    // HomePageController.java
    public void setUserDetails() {
        String username = UserSession.getUsername();
        String email = UserSession.getEmail();

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
