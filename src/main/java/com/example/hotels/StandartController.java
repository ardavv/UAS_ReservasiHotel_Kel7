package com.example.hotels;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class StandartController {

    @FXML
    private VBox C301, C302, C303, C304, C305, C306;

    // Variabel untuk menyimpan referensi VBox yang dipilih
    private VBox selectedRoomBox = null;

    @FXML
    void onC301Clicked() {
        handleRoomClick(C301, "Kamar C-301");
    }

    @FXML
    void onC302Clicked() {
        handleRoomClick(C302, "Kamar C-302");
    }

    @FXML
    void onC303Clicked() {
        handleRoomClick(C303, "Kamar C-303");
    }

    @FXML
    void onC304Clicked() {
        handleRoomClick(C304, "Kamar C-304");
    }

    @FXML
    void onC305Clicked() {
        handleRoomClick(C305, "Kamar C-305");
    }

    @FXML
    void onC306Clicked() {
        handleRoomClick(C306, "Kamar C-306");
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
}
