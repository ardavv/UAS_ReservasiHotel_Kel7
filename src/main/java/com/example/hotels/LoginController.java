package com.example.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Ambil data pengguna dari RegisterController
        HashMap<String, String> users = RegisterController.getUsers();

        if (username.isEmpty() || password.isEmpty()) {
            setError("Semua field wajib diisi.");
        } else if (!users.containsKey(username)) {
            setError("Username tidak ditemukan.");
        } else if (!users.get(username).equals(password)) {
            setError("Password salah.");
        } else {
            setSuccess("Login berhasil!");
        }
    }

    @FXML
    private void handleHyperlinkToRegister(ActionEvent event) {
        try {
            // Muat file FXML untuk register-view
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ambil stage utama dan ubah scene-nya
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
