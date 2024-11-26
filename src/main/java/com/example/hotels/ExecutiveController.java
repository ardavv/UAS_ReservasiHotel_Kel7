package com.example.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ExecutiveController {

    @FXML
    private VBox A101, A102, A103, A104;

    // Variabel untuk menyimpan referensi VBox yang dipilih
    private VBox selectedRoomBox = null;

    @FXML
    void onA101Clicked() {
        handleRoomClick(A101, "Kamar A-101");
    }

    @FXML
    void onA102Clicked() {
        handleRoomClick(A102, "Kamar A-102");
    }

    @FXML
    void onA103Clicked() {
        handleRoomClick(A103, "Kamar A-103");
    }

    @FXML
    void onA104Clicked() {
        handleRoomClick(A104, "Kamar A-104");
    }

    private void handleRoomClick(VBox roomBox, String roomName) {
        // Reset warna VBox sebelumnya
        if (selectedRoomBox != null) {
            selectedRoomBox.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 9, 0.1, 0, 0);");
        }

        // Ubah warna VBox yang baru dipilih
        roomBox.setStyle("-fx-background-color: #FF9C10; -fx-background-radius: 20px");

        // Perbarui referensi ke VBox yang dipilih
        selectedRoomBox = roomBox;

        // Simpan data kamar ke utilitas
        RoomSelection.setSelectedRoom(roomName);

        // Debug: Tampilkan data kamar yang tersimpan
        System.out.println("Selected Room: " + RoomSelection.getSelectedRoom());
    }

    @FXML
    private void onBackClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home Room");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
