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
        roomStatus.put("Kamar B-201", false); // Kamar ini masih tersedia
        roomStatus.put("Kamar B-202", true);  // Kamar ini sudah dibooking
        roomStatus.put("Kamar B-203", false); // Kamar ini masih tersedia
        roomStatus.put("Kamar B-204", true);  // Kamar ini sudah dibooking
        roomStatus.put("Kamar B-205", false); // Kamar ini masih tersedia
    }

    @FXML
    void onB201Clicked() {
        handleRoomClick(B201, "Kamar B-201", "Deluxe", 400000);
    }

    @FXML
    void onB202Clicked() {
        handleRoomClick(B202, "Kamar B-202", "Deluxe", 400000);
    }

    @FXML
    void onB203Clicked() {
        handleRoomClick(B203, "Kamar B-203", "Deluxe", 400000);
    }

    @FXML
    void onB204Clicked() {
        handleRoomClick(B204, "Kamar B-204", "Deluxe", 400000);
    }

    @FXML
    void onB205Clicked() {
        handleRoomClick(B205, "Kamar B-205", "Deluxe", 400000);
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
