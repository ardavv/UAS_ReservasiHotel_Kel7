package com.example.hotels;

public class RoomSelection {
    private static String selectedRoom;
    private static String roomType;
    private static int roomPrice;

    public static String getSelectedRoom() {
        return selectedRoom;
    }

    public static void setSelectedRoom(String room, String type, int price) {
        selectedRoom = room;
        roomType = type;
        roomPrice = price;
    }

    public static String getRoomType() {
        return roomType;
    }

    public static int getRoomPrice() {
        return roomPrice;
    }
}
