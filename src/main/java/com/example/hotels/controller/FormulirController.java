package com.example.hotels.controller;

import com.example.hotels.database.Room;
import com.example.hotels.database.RoomSelection;
import com.example.hotels.database.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;


public class FormulirController {

    @FXML
    TextField namaLengkap;

    @FXML
    TextField noTelepon;

    @FXML
    TextField email;

    @FXML
    TextField domisili;

    @FXML
    TextField jumlahOrang;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private Label durasiPesan;

    public void switchScenetoPembayaran(ActionEvent event) throws IOException {
        try {
            // Muat file FXML untuk login-view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/pembayaran-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ambil stage utama dan ubah scene-nya
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Pembayaran Page");
            stage.show();
            RoomSelection.setFormulirData(namaLengkap.getText(), noTelepon.getText(), email.getText(), domisili.getText(), jumlahOrang.getText(), checkInDate.getValue(), checkOutDate.getValue());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void checkOutDateClicked(ActionEvent event) throws ParseException {
        if (checkInDate.getValue() != null && checkOutDate.getValue() != null) {
            long daysBetween = ChronoUnit.DAYS.between(checkInDate.getValue(), checkOutDate.getValue());
            System.out.println("Selisih hari: " + daysBetween + " hari");
            durasiPesan.setText(daysBetween + " hari");
            RoomSelection.setDurasiPesan(daysBetween);
            System.out.println("Durasi: "+RoomSelection.getDurasiPesan());
        } else {
            System.out.println("Tanggal Check-In dan Check-Out harus dipilih!");
        }
    }
}