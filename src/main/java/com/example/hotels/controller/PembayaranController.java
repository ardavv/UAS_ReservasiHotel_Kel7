package com.example.hotels.controller;

import com.example.hotels.database.UserSession;
import com.example.hotels.database.RoomSelection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class PembayaranController implements Initializable {


    @FXML
    private ChoiceBox<String> choiceBank;

    @FXML
    private TextField noRek;

    @FXML
    private Text totalBayar;

    @FXML
    private Text sumNamaKamar;
    @FXML
    private Text sumJenisKamar;
    @FXML
    private Text sumHargaKamar;
    @FXML
    private Text sumDurasiKamar;

    private String[] listBank = {"VA BCA", "VA BRI", "VA BNI", "VA Mandiri"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Tambahkan listener untuk menangani perubahan pilihan
        choiceBank.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Generate nomor rekening baru setiap kali bank dipilih
                noRek.setText(generateAccountNumber());
                setHarga();
            }
        });

        choiceBank.getItems().addAll(listBank);


    }

    public void switchScenetoBerhasil(ActionEvent event) throws IOException {
        try {
            // Muat file FXML untuk login-view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/berhasil-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            RoomSelection.setMetodePembayaran(choiceBank.getValue());

            // Ambil stage utama dan ubah scene-nya
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Pembayaran Berhasil");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder();

        // Mengisi dengan angka secara acak hingga mencapai panjang yang diinginkan
        for (int i = 0; i < 16; i++) {
            accountNumber.append(random.nextInt(10)); // Generate angka 0-9
        }

        return accountNumber.toString();
    }

    public void setHarga() {
        int harga = RoomSelection.getRoomPrice();
        long durasi = RoomSelection.getDurasiPesan();

        long total = harga*durasi;
        System.out.println(total);
        System.out.println(harga);
        System.out.println(durasi);


        totalBayar.setText(String.valueOf(total));
        sumNamaKamar.setText("Nama Kamar: "+RoomSelection.getSelectedRoom());
        sumJenisKamar.setText("Jenis Kamar: "+RoomSelection.getRoomType());
        sumHargaKamar.setText("Harga Kamar: "+ RoomSelection.getRoomPrice());
        sumDurasiKamar.setText("Durasi Pemesanan: "+ RoomSelection.getDurasiPesan()+" Hari");

    }
}
