package com.example.hotels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class RegisterController {

    private static final HashMap<String, String> users = new HashMap<>(); // Username -> Password
    private static final HashMap<String, String> emails = new HashMap<>(); // Username -> Email

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
        handleRegistration();
    }

    @FXML
    private void handleFieldKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (usernameField.isFocused()) {
                emailField.requestFocus(); // Pindah ke field email
            } else if (emailField.isFocused()) {
                passwordField.requestFocus(); // Pindah ke field password
            } else if (passwordField.isFocused()) {
                confirmPasswordField.requestFocus(); // Pindah ke field konfirmasi password
            } else if (confirmPasswordField.isFocused()) {
                handleRegistration(); // Lakukan registrasi
            }
        }
    }

    private void handleRegistration() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            setError("Semua field wajib diisi.");
        } else if (users.containsKey(username)) {
            setError("Username sudah terdaftar.");
        } else if (!password.equals(confirmPassword)) {
            setError("Password tidak cocok.");
        } else if (!isValidEmail(email)) {
            setError("Email tidak valid.");
        } else {
            users.put(username, password);
            emails.put(username, email);

            // Simpan username dan email ke dalam UserSession
            UserSession.setUsername(username);
            UserSession.setEmail(email);

            clearFields();
            setSuccess("Registrasi berhasil! Anda dapat login sekarang.");
        }
    }

    @FXML
    private void handleHyperlinkToLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
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

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static HashMap<String, String> getUsers() {
        return users;
    }

    public static String getEmail(String username) {
        return emails.get(username);
    }
}
