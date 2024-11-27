package com.example.hotels.controller;

import com.example.hotels.database.RoomSelection;
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
    private Label warningLabel; // Label untuk menampilkan pesan peringatan

    private VBox selectedRoomBox = null; // Referensi ke VBox yang dipilih

    private final HashMap<String, Boolean> roomStatus = new HashMap<>(); // Status booking kamar

    @FXML
    public void initialize() {
        // Status booking kamar (false = tersedia, true = dibooking)
        roomStatus.put("Kamar A-101", false);
        roomStatus.put("Kamar A-102", true); // Kamar ini sudah dibooking
        roomStatus.put("Kamar A-103", false);
        roomStatus.put("Kamar A-104", true); // Kamar ini sudah dibooking
    }

    @FXML
    void onA101Clicked() {
        handleRoomClick(A101, "Kamar A-101", "Executive", 600000);
    }

    @FXML
    void onA102Clicked() {
        handleRoomClick(A102, "Kamar A-102", "Executive", 600000);
    }

    @FXML
    void onA103Clicked() {
        handleRoomClick(A103, "Kamar A-103", "Executive", 600000);
    }

    @FXML
    void onA104Clicked() {
        handleRoomClick(A104, "Kamar A-104", "Executive", 600000);
    }

    private void handleRoomClick(VBox roomBox, String roomName, String roomType, int roomPrice) {
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

        // Simpan data kamar ke utilitas RoomSelection
        RoomSelection.setSelectedRoom(roomName, roomType, roomPrice);
    }

    @FXML
    private void onPesanClicked(ActionEvent event) {
        // Cek apakah kamar sudah dibooking
        String selectedRoom = RoomSelection.getSelectedRoom();
        if (roomStatus.getOrDefault(selectedRoom, false)) {
            warningLabel.setText("Kamu tidak bisa memesannya karena sudah dibooking orang lain.");
            warningLabel.setStyle("-fx-text-fill: red;");
        } else {
            // Pesan kamar berhasil
            System.out.println("Room Type: " + RoomSelection.getRoomType());
            System.out.println("Room Number: " + RoomSelection.getSelectedRoom());
            System.out.println("Room Price: " + RoomSelection.getRoomPrice());

            // Di sini bisa ditambahkan proses pemesanan atau tampilan konfirmasi
            warningLabel.setText("Kamar berhasil dipesan.");
            warningLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    private void onBackClick(ActionEvent event) {
        try {
            // Muat halaman home-view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ambil controller HomePage
            HomePageController homeController = fxmlLoader.getController();

            // Set data user (username dan email) yang sudah disimpan di UserSession
            homeController.setUserDetails(); // Memanggil setUserDetails() yang tidak membutuhkan parameter

            // Tampilkan halaman utama
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home Room");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
