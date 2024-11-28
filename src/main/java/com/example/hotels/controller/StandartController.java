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

public class StandartController {

    @FXML
    private VBox C301, C302, C303, C304, C305, C306;

    @FXML
    private Label warningLabel; // Label untuk menampilkan pesan peringatan

    private VBox selectedRoomBox = null; // Referensi ke VBox yang dipilih

    private final HashMap<String, Boolean> roomStatus = new HashMap<>(); // Status booking kamar

    @FXML
    public void initialize() {
        // Status booking kamar (false = tersedia, true = dibooking)
        roomStatus.put("Kamar C-301", false);
        roomStatus.put("Kamar C-302", true); // Kamar ini sudah dibooking
        roomStatus.put("Kamar C-303", false);
        roomStatus.put("Kamar C-304", false);
        roomStatus.put("Kamar C-305", true); // Kamar ini sudah dibooking
        roomStatus.put("Kamar C-306", false);
    }

    @FXML
    void onC301Clicked() {
        handleRoomClick(C301, "Kamar C-301", "Standard", 200000);
    }

    @FXML
    void onC302Clicked() {
        handleRoomClick(C302, "Kamar C-302", "Standard", 200000);
    }

    @FXML
    void onC303Clicked() {
        handleRoomClick(C303, "Kamar C-303", "Standard", 200000);
    }

    @FXML
    void onC304Clicked() {
        handleRoomClick(C304, "Kamar C-304", "Standard", 200000);
    }

    @FXML
    void onC305Clicked() {
        handleRoomClick(C305, "Kamar C-305", "Standard", 200000);
    }

    @FXML
    void onC306Clicked() {
        handleRoomClick(C306, "Kamar C-306", "Standard", 200000);
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

            // switch screen untuk manjutkan ke halaman pengisian data pemesan
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/formulir-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                // Ambil stage saat ini dan ubah scene-nya
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Isi Data Pemesan");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
