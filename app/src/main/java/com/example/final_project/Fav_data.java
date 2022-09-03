package com.example.final_project;

import androidx.annotation.NonNull;

public class Fav_data {
    private String name;
    private String category;
    private int img;

    public Fav_data(String name, String category, int img) {
        this.name = name;
        this.category = category;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
