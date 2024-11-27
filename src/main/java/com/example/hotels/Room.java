package com.example.hotels;

public class Room {
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private String fxmlFile;
    private double price; // Harga ruangan

    // Constructor with price
    public Room(String id, String name, String imageUrl, String description, String fxmlFile, double price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.fxmlFile = fxmlFile;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

    public double getPrice() {
        return price;
    }
}
