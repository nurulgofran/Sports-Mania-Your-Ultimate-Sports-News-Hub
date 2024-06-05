package com.nurul.sportmania.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SESAM on 02.09.2018.
 */

public class SharedPref {
    SharedPreferences preferences;

    public SharedPref(Context context){
        preferences = context.getSharedPreferences("Sherrif",Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }

    public boolean loadNightModeState(){
        Boolean aBoolean = preferences.getBoolean("NightMode",false);
        return aBoolean;
    }

    public void setSavingModeState(Boolean state){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("SavingMode",state);
        editor.commit();
    }

    public boolean loadSavingModeState(){
        Boolean aBoolean = preferences.getBoolean("SavingMode",false);
        return aBoolean;
    }

    public void setLoyout(int state){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("LayoutMode",state);
        editor.commit();
    }

    public int loadLayout(){
        int i = preferences.getInt("LayoutMode",0);
        return i;
    }

    public void setFavoriteState(Boolean state){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Favorite",state);
        editor.commit();
    }

    public boolean loadFavoriteState(){
        Boolean aBoolean = preferences.getBoolean("Favorite",false);
        return aBoolean;
    }


    public void setLoginState(Boolean state){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Login",state);
        editor.commit();
    }

    public boolean loadLoginState(){
        Boolean aBoolean = preferences.getBoolean("Login",false);
        return aBoolean;
    }

    public void setUser(String field, String text){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(field,text);
        editor.commit();
    }

    public String loadUser(String field){
        String text = preferences.getString(field," ");
        return text;
    }

}
