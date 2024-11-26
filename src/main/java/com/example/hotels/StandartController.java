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

public class StandartController {

    @FXML
    private VBox C301, C302, C303, C304, C305, C306;

    @FXML
    private Label warningLabel; // Tambahkan label untuk menampilkan pesan peringatan

    // Variabel untuk menyimpan referensi VBox yang dipilih
    private VBox selectedRoomBox = null;

    // Simpan status booking kamar
    private final HashMap<String, Boolean> roomStatus = new HashMap<>();

    @FXML
    public void initialize() {
        // Tandai kamar yang sudah dibooking
        roomStatus.put("Kamar C-301", false);
        roomStatus.put("Kamar C-302", true); // Contoh: kamar ini sudah dibooking
        roomStatus.put("Kamar C-303", false);
        roomStatus.put("Kamar C-304", false);
        roomStatus.put("Kamar C-305", true); // Contoh: kamar ini sudah dibooking
        roomStatus.put("Kamar C-306", false);
    }

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
