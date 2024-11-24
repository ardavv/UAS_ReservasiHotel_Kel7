package com.example.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class RegisterController {

    // Static HashMap untuk menyimpan data pengguna
    private static final HashMap<String, String> users = new HashMap<>();

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorHandle;

    @FXML
    protected void onRegisterButtonClick() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validasi input
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            setError("Semua field wajib diisi.");
        } else if (users.containsKey(username)) {
            setError("Username sudah terdaftar.");
        } else if (!password.equals(confirmPassword)) {
            setError("Password tidak cocok.");
        } else {
            // Simpan data ke HashMap
            users.put(username, password);
            clearFields();
            setSuccess("Registrasi berhasil! Anda dapat login sekarang.");
        }
    }

    @FXML
    private void handleHyperlinkToLogin(ActionEvent event) {
        try {
            // Muat file FXML untuk login-view
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Ambil stage utama dan ubah scene-nya
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Page");
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

    private void clearFields() {
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    // Getter untuk HashMap pengguna
    public static HashMap<String, String> getUsers() {
        return users;
    }
}
