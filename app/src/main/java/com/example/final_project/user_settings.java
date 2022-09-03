package com.example.final_project;

import android.app.Application;

public class user_settings extends Application {
    public static final String preferances = "preferances";
    public static final String custom_theme = "custom_theme";
    public static final String light_theme = "light_theme";
    public static final String dark_theme = "dark_theme";
    private String custom_mode;

    public String getCustom_mode() {
        return custom_mode;
    }

    public void setCustom_mode(String custom_mode) {
        this.custom_mode = custom_mode;
    }
}
