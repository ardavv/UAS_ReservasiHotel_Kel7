package com.example.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorHandle;

    private static String activeUsername;
    private static String activeEmail;

    public static String getActiveUsername() {
        return activeUsername;
    }

    public static String getActiveEmail() {
        return activeEmail;
    }

    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        HashMap<String, String> users = RegisterController.getUsers();

        if (username.isEmpty() || password.isEmpty()) {
            setError("Semua field wajib diisi.");
        } else if (!users.containsKey(username)) {
            setError("Username tidak ditemukan.");
        } else if (!users.get(username).equals(password)) {
            setError("Password salah.");
        } else {
            // Simpan username dan email aktif
            activeUsername = username;
            activeEmail = RegisterController.getEmail(username);

            // Lanjutkan ke halaman utama
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home-view.fxml"));
                AnchorPane homePage = loader.load();

                // Ambil controller untuk HomePage
                HomePageController homeController = loader.getController();
                homeController.setUserDetails(activeUsername, activeEmail); // Kirim data username dan email

                // Tampilkan scene baru
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = new Scene(homePage);
                stage.setScene(scene);
                stage.setTitle("Home Page");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleHyperlinkToRegister(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setError(String message) {
        errorHandle.setText(message);
        errorHandle.setStyle("-fx-text-fill: red;");
    }

    private void setSuccess(String message) {
        errorHandle.setText(message);
        errorHandle.setStyle("-fx-text-fill: green;");
    }
}
