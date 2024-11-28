package com.example.hotels.database;

import java.time.LocalDate;

public class RoomSelection {
    private static String selectedRoom;
    private static String roomType;
    private static int roomPrice;
    private static long durasiPesan;
    private static String namaLengkap;
    private static String noTelepon;
    private static String email;
    private static String domisili;
    private static String jumlahOrang;
    private static LocalDate dateCheckIn;
    private static LocalDate dateCheckOut;
    private static String metodePembayaran;

    public static String getSelectedRoom() {
        return selectedRoom;
    }

    public static void setSelectedRoom(String room, String type, int price) {
        selectedRoom = room;
        roomType = type;
        roomPrice = price;
    }

    public static void setFormulirData(String Nama, String NoTelp, String Email, String Domisili, String JumlahOrang, LocalDate DateCheckIn, LocalDate DateCheckOut) {
        namaLengkap = Nama;
        noTelepon = NoTelp;
        email = Email;
        domisili = Domisili;
        jumlahOrang = JumlahOrang;
        dateCheckIn = DateCheckIn;
        dateCheckOut = DateCheckOut;
    }

    public static String getMetodePembayaran() {
        return metodePembayaran;
    }

    public static void setMetodePembayaran(String metodeBayar) {
        metodePembayaran = metodeBayar;
    }

    public static String getNamaLengkap() {
        return namaLengkap;
    }

    public static String getNoTelepon() {
        return noTelepon;
    }

    public static String getEmail() {
        return email;
    }

    public static String getDomisili() {
        return domisili;
    }

    public static String getJumlahOrang() {
        return jumlahOrang;
    }

    public static LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public static LocalDate getDateCheckOut() {
        return dateCheckOut;
    }

    public static long getDurasiPesan() {
        System.out.println("Durasi: "+durasiPesan);
        return durasiPesan;
    }

    public static void setDurasiPesan(long durasiPemesanan) {
        durasiPesan = durasiPemesanan;
    }

    public static String getRoomType() {
        return roomType;
    }

    public static int getRoomPrice() {
        return roomPrice;
    }
}
