package com.nurul.sportmania.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.R;

public class SettingsActivity extends AppCompatActivity {

    private Switch dark_mode,card_view,saving_data;
    SharedPref pref;
    private ActionBar toolbar;
    private long backPressedTime = 0;
    private TextView enable_dark_mode,night_mode,enable_cardview_layout,cardview_layout,enable_saving_data,savingdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new SharedPref(this);
        if(pref.loadNightModeState() == true){
            setTheme(R.style.AppThemeNight);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(pref.loadUser("TitleSettings"));
        //Translations
        enable_dark_mode = (TextView) findViewById(R.id.enable_dark_mode);
        enable_dark_mode.setText(pref.loadUser("EnableNightMode"));

        night_mode = (TextView) findViewById(R.id.night_mode);
        night_mode.setText(pref.loadUser("Night_Mode"));

        enable_cardview_layout = (TextView) findViewById(R.id.enable_cardview_layout);
        enable_cardview_layout.setText(pref.loadUser("EnableCardviewLayout"));

        cardview_layout = (TextView) findViewById(R.id.enable_saving_data);
        cardview_layout.setText(pref.loadUser("CardviewLayout"));

        enable_dark_mode = (TextView) findViewById(R.id.enable_saving_data);
        enable_dark_mode.setText(pref.loadUser("EnableSavingData"));

        savingdata = (TextView) findViewById(R.id.saving_data);
        savingdata.setText(pref.loadUser("SavingData"));
        //Translations
        dark_mode = (Switch) findViewById(R.id.switch_dark_mode);
        card_view = (Switch) findViewById(R.id.switch_card_view);
        saving_data = (Switch) findViewById(R.id.switch_saving_data);

        if(pref.loadNightModeState() == true){
            dark_mode.setChecked(true);
        }

        if(pref.loadSavingModeState() == true){
            saving_data.setChecked(true);
        }

        if(pref.loadLayout() == 1){
            card_view.setChecked(true);
        }

        dark_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pref.setNightModeState(true);
                    ReStartApp();
                }else{
                    pref.setNightModeState(false);
                    ReStartApp();
                }
            }
        });

        card_view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pref.setLoyout(1);
                    ReStartApp();
                }else{
                    pref.setLoyout(0);
                    ReStartApp();
                }
            }
        });

        saving_data.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pref.setSavingModeState(true);
                    ReStartApp();
                }else{
                    pref.setSavingModeState(false);
                    ReStartApp();
                }
            }
        });

    }

    public  void ReStartApp(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),pref.loadUser("SettingsSaved"),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }




}
