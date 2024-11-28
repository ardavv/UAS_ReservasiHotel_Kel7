package com.example.hotels.controller;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BerhasilController implements Initializable {

    @FXML
    Button lanjutkanButton;

    @FXML
    ImageView imagePembayaran;

    @FXML
    Text textStatusPembayaran;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        untuk memutar login icon
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(imagePembayaran);
        rotate.setDuration(Duration.seconds(2));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setByAngle(360);
        rotate.play();
//        menunggu 5 detik untuk memunculkan button lanjutkan dan mengubah gambar loading menjadi success
        lanjutkanButton.setVisible(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e-> {
            lanjutkanButton.setVisible(true);
            rotate.stop();
            imagePembayaran.setRotate(0);
            Image newImage = new Image(getClass().getResourceAsStream("/com/example/hotels/image/success-icon.png"));
            imagePembayaran.setImage(newImage);
            textStatusPembayaran.setText("Pembayaran Berhasil");

        }));
        timeline.play();
    }

    public void switchScenetoSummary(ActionEvent event) throws IOException {
        try {
            // Muat file FXML untuk summary-view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/summary-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ambil stage utama dan ubah scene-nya
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Summary Pemesanan");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
