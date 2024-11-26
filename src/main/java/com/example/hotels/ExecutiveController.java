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

public class ExecutiveController {

    @FXML
    private VBox A101, A102, A103, A104;

    @FXML
    private Label warningLabel; // Tambahkan label untuk menampilkan pesan peringatan

    // Variabel untuk menyimpan referensi VBox yang dipilih
    private VBox selectedRoomBox = null;

    // Simpan status booking kamar
    private final HashMap<String, Boolean> roomStatus = new HashMap<>();

    @FXML
    public void initialize() {
        // Tandai kamar yang sudah dibooking
        roomStatus.put("Kamar A-101", false);
        roomStatus.put("Kamar A-102", true); // Contoh: kamar ini sudah dibooking
        roomStatus.put("Kamar A-103", false);
        roomStatus.put("Kamar A-104", true); // Contoh: kamar ini sudah dibooking
    }

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
