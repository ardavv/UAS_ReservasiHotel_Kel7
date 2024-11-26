package com.example.hotels;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DeluxeController {

    @FXML
    private VBox B201, B202, B203, B204, B205;

    // Variabel untuk menyimpan referensi VBox yang dipilih
    private VBox selectedRoomBox = null;

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
        // Ubah warna hanya untuk VBox yang dipilih
        if (selectedRoomBox != null) {
            // Reset warna VBox sebelumnya
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
}
