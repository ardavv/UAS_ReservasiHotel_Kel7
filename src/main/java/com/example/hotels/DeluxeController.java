package com.example.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class DeluxeController {

    @FXML
    private VBox B201, B202, B203, B204, B205;

    @FXML
    private Label warningLabel; // Label untuk menampilkan pesan peringatan

    // Variabel untuk menyimpan referensi VBox yang dipilih
    private VBox selectedRoomBox = null;

    // Simpan status booking kamar
    private final HashMap<String, Boolean> roomStatus = new HashMap<>();

    @FXML
    public void initialize() {
        // Tandai kamar yang sudah dibooking
        roomStatus.put("Kamar B-201", false); // Contoh: kamar ini masih tersedia
        roomStatus.put("Kamar B-202", true);  // Contoh: kamar ini sudah dibooking
        roomStatus.put("Kamar B-203", false); // Contoh: kamar ini masih tersedia
        roomStatus.put("Kamar B-204", true);  // Contoh: kamar ini sudah dibooking
        roomStatus.put("Kamar B-205", false); // Contoh: kamar ini masih tersedia
    }

    @FXML
    void onB201Clicked() {
        handleRoomClick(B201, "Kamar B-201");
    }

    @FXML
    void onB202Clicked() {
        handleRoomClick(B202, "Kamar B-202");
    }

    @FXML
    void onB203Clicked() {
        handleRoomClick(B203, "Kamar B-203");
    }

    @FXML
    void onB204Clicked() {
        handleRoomClick(B204, "Kamar B-204");
    }

    @FXML
    void onB205Clicked() {
        handleRoomClick(B205, "Kamar B-205");
    }

    private void handleRoomClick(VBox roomBox, String roomName) {
        // Reset warna VBox sebelumnya
        if (selectedRoomBox != null) {
            selectedRoomBox.setStyle("-fx-background-color: white; -fx-background-radius: 20px;");
        }

        // Ubah warna VBox yang baru dipilih
        roomBox.setStyle("-fx-background-color: #FF9C10; -fx-background-radius: 20px");

        // Perbarui referensi ke VBox yang dipilih
        selectedRoomBox = roomBox;

        // Periksa status booking kamar
        if (roomStatus.getOrDefault(roomName, false)) {
            warningLabel.setText("Kamar sudah dibooking!");
            warningLabel.setStyle("-fx-text-fill: red;");
        } else {
            warningLabel.setText(""); // Kosongkan peringatan jika kamar tersedia
        }

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
