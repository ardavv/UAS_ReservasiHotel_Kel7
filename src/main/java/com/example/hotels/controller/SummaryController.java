package com.example.hotels.controller;

import com.example.hotels.database.RoomSelection;
import com.example.hotels.database.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {

    @FXML
    Text namaLengkap;

    @FXML
    Text email;

    @FXML
    Text noTelp;

    @FXML
    Text domisili;

    @FXML
    Text namaRuang;

    @FXML
    Text jumlahOrang;

    @FXML
    Text checkInDate;

    @FXML
    Text checkOutDate;

    @FXML
    Text metodePembayaran;

    @FXML
    Text totalBayar;

    @FXML
    Text sumJenisRuangan;

    public void setHarga() {
        int harga = RoomSelection.getRoomPrice();
        long durasi = RoomSelection.getDurasiPesan();

        long total = harga*durasi;
        System.out.println(total);
        System.out.println(harga);
        System.out.println(durasi);


        namaLengkap.setText(RoomSelection.getNamaLengkap());
        email.setText(RoomSelection.getEmail());
        noTelp.setText(RoomSelection.getNoTelepon());
        domisili.setText(RoomSelection.getDomisili());
        namaRuang.setText(RoomSelection.getSelectedRoom());
        jumlahOrang.setText(RoomSelection.getJumlahOrang());
        checkInDate.setText(String.valueOf(RoomSelection.getDateCheckIn()));
        checkOutDate.setText(String.valueOf(RoomSelection.getDateCheckOut()));
        metodePembayaran.setText(RoomSelection.getMetodePembayaran());
        sumJenisRuangan.setText(RoomSelection.getRoomType());
        totalBayar.setText(String.valueOf(total));

        System.out.println(RoomSelection.getNamaLengkap());
        System.out.println(RoomSelection.getEmail());
    }

    public void switchScenetoHome(ActionEvent event) throws IOException {
        try {
            // Muat file FXML untuk home-view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ambil stage utama dan ubah scene-nya
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home Page");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHarga();
    }
}
