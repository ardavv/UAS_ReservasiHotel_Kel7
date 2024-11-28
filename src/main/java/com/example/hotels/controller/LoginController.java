package com.example.hotels.controller;

import com.example.hotels.database.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

    // LoginController.java
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
            // Simpan username dan email aktif ke UserSession
            String activeEmail = RegisterController.getEmail(username);
            UserSession.setUsername(username);
            UserSession.setEmail(activeEmail);

            // Lanjutkan ke halaman utama
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotels/home-view.fxml"));
                AnchorPane homePage = loader.load();



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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hotels/register-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFieldKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER -> {
                if (usernameField.isFocused()) {
                    passwordField.requestFocus(); // Pindah ke passwordField
                } else if (passwordField.isFocused()) {
                    onLoginButtonClick(); // Login jika passwordField di-enter
                }
            }
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
