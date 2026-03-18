package com.nurul.sportmania.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.nurul.sportmania.ads.AdManager;
import com.nurul.sportmania.data.preference.AppPreferences;
import com.nurul.sportmania.R;

public class TermsActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private AppPreferences pref;
    private TextView terms,privacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new AppPreferences(this);
        if(pref.loadNightModeState() == true){
            setTheme(R.style.AppThemeNight);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        AdManager.createLoadInterstitial(getApplicationContext(),null);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.title_terms);

        terms = (TextView) findViewById(R.id.terms);
        privacy = (TextView) findViewById(R.id.privacy);

        terms.setText(pref.loadUser("AppTerms"));
        privacy.setText(pref.loadUser("AppPrivacy"));
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
        finish();
    }
}
