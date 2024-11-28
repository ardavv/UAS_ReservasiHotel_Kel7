package com.example.hotels.database;

public class UserSession {
    private static String username;
    private static String email;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserSession.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserSession.email = email;
    }
}
