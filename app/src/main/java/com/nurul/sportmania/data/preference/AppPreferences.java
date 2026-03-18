package com.nurul.sportmania.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static final String PREFERENCES_NAME = "Sherrif";
    private final SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state) {
        preferences.edit().putBoolean("NightMode", state).apply();
    }

    public boolean loadNightModeState() {
        return preferences.getBoolean("NightMode", false);
    }

    public void setSavingModeState(Boolean state) {
        preferences.edit().putBoolean("SavingMode", state).apply();
    }

    public boolean loadSavingModeState() {
        return preferences.getBoolean("SavingMode", false);
    }

    public void setLoyout(int state) {
        preferences.edit().putInt("LayoutMode", state).apply();
    }

    public int loadLayout() {
        return preferences.getInt("LayoutMode", 0);
    }

    public void setFavoriteState(Boolean state) {
        preferences.edit().putBoolean("Favorite", state).apply();
    }

    public boolean loadFavoriteState() {
        return preferences.getBoolean("Favorite", false);
    }

    public void setLoginState(Boolean state) {
        preferences.edit().putBoolean("Login", state).apply();
    }

    public boolean loadLoginState() {
        return preferences.getBoolean("Login", false);
    }

    public void setUser(String field, String text) {
        preferences.edit().putString(field, text).apply();
    }

    public String loadUser(String field) {
        return preferences.getString(field, " ");
    }
}
