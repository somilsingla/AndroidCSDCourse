package com.example.androidcsdcourse.models;


public class Item {
    String name;
    String details;
    int image_id;

    public Item(String name, String details, int image_id) {
        this.name = name;
        this.details = details;
        this.image_id = image_id;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
