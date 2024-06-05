package com.nurul.sportmania.Activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nurul.sportmania.Helpers.ApiClient;
import com.nurul.sportmania.Helpers.Functions;
import com.nurul.sportmania.Helpers.NewsInterface;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.MainActivity;
import com.nurul.sportmania.Models.Settings;
import com.nurul.sportmania.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    SharedPref pref;
    private ProgressBar pb_settings;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        pref = new SharedPref(this);
        if(pref.loadNightModeState() == true){
            setTheme(R.style.AppThemeNight);
        }else{
            setTheme(R.style.AppThemeLight);
        }

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        logo = (ImageView) findViewById(R.id.imageView);

        if(pref.loadNightModeState()){
            Picasso.with(this).cancelRequest(logo);
            Picasso.with(this).load(R.drawable.ic_launcher_dark).placeholder((int) R.drawable.ic_launcher_dark).error((int) R.drawable.ic_launcher_dark).into(logo);
        }else{
            Picasso.with(this).cancelRequest(logo);
            Picasso.with(this).load(R.drawable.ic_launcher).placeholder((int) R.drawable.ic_launcher).error((int) R.drawable.ic_launcher).into(logo);

        }

        pb_settings = (ProgressBar)findViewById(R.id.pb_settings);

        if (Functions.checkConnection(this)) {
            LoadSettings();

            new CountDownTimer(3000, 1000) {
                public void onTick(long j) {
                }
                public void onFinish() {
                    pb_settings.setVisibility(View.GONE);
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this.getBaseContext(), MainActivity.class));
                    SplashActivity.this.finish();
                }
            }.start();
        }else{
            Functions.Message(getApplicationContext(), getResources().getString(R.string.internet_error));
        }

    }

    private void LoadSettings(){

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<Settings> call = apiService.getSettings();

        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(retrofit2.Call<Settings> call, Response<Settings> response) {
                Settings c = response.body();
                List<Settings.Setting> detail = c.getSettings();
                pref.setUser("AppName", detail.get(0).getAppName().toString());
                pref.setUser("AppDescription", detail.get(0).getAppDescription().toString());
                pref.setUser("AppEmail", detail.get(0).getAppEmail().toString());
                pref.setUser("AppLogo", detail.get(0).getAppLogo().toString());
                pref.setUser("AppWebsite", detail.get(0).getAppWebsite().toString());
                pref.setUser("AppPrivacy", detail.get(0).getAppPrivacy().toString());
                pref.setUser("AppTerms", detail.get(0).getAppTerms().toString());
                pref.setUser("AppToken", detail.get(0).getAppToken().toString());

                pref.setUser("InterstialAds", detail.get(0).getAdmobInterstialKey().toString());
                pref.setUser("BannerAds", detail.get(0).getAdmobBannerKey().toString());


                pref.setUser("AdmobStatus", detail.get(0).getAdmobStatus().toString());

                //Translations
                pref.setUser("TitleHome", detail.get(0).getTitleHome().toString());
                pref.setUser("TitleCategory", detail.get(0).getTitleCategory().toString());
                pref.setUser("TitleVideo", detail.get(0).getTitleVideo().toString());
                pref.setUser("TitleFavorite", detail.get(0).getTitleFavorite().toString());
                pref.setUser("TitleProfile", detail.get(0).getTitleProfile().toString());
                pref.setUser("TitleSettings", detail.get(0).getTitleSettings().toString());
                pref.setUser("TitleLogin", detail.get(0).getTitleLogin().toString());
                pref.setUser("TitleRegister", detail.get(0).getTitleRegister().toString());
                pref.setUser("TitleAbout", detail.get(0).getTitleAbout().toString());
                pref.setUser("ActionSettings", detail.get(0).getActionSettings().toString());
                pref.setUser("TitleActiviyNewsDetail", detail.get(0).getTitleActiviyNewsDetail().toString());
                pref.setUser("Search", detail.get(0).getSearch().toString());
                pref.setUser("SearchHere", detail.get(0).getSearchHere().toString());
                pref.setUser("TimeoutError", detail.get(0).getTimeoutError().toString());
                pref.setUser("HintEmail", detail.get(0).getHintEmail().toString());
                pref.setUser("SettingsSaved", detail.get(0).getSettingsSaved().toString());
                pref.setUser("Succesfull", detail.get(0).getSuccesfull().toString());
                pref.setUser("Error", detail.get(0).getError().toString());
                pref.setUser("HintPassword", detail.get(0).getHintPassword().toString());
                pref.setUser("HintName", detail.get(0).getHintName().toString());
                pref.setUser("YouHavaRated", detail.get(0).getYouHavaRated().toString());
                pref.setUser("BtnLogin", detail.get(0).getBtnLogin().toString());
                pref.setUser("BtnRegister", detail.get(0).getBtnRegister().toString());
                pref.setUser("BtnLinkToRegister", detail.get(0).getBtnLinkToRegister().toString());
                pref.setUser("BtnLinkToLogin", detail.get(0).getBtnLinkToLogin().toString());
                pref.setUser("Welcome", detail.get(0).getWelcome().toString());
                pref.setUser("BtnLogout", detail.get(0).getBtnLogout().toString());
                pref.setUser("Name", detail.get(0).getName().toString());
                pref.setUser("NotLoginFavoriteMessage", detail.get(0).getNotLoginFavoriteMessage().toString());
                pref.setUser("PleaseFillCredentials", detail.get(0).getPleaseFillCredentials().toString());
                pref.setUser("PleaseFillTheFields", detail.get(0).getPleaseFillTheFields().toString());
                pref.setUser("YouMustSignToFavorites", detail.get(0).getYouMustSignToFavorites().toString());
                pref.setUser("ValidEmailAdres", detail.get(0).getValidEmailAdres().toString());
                pref.setUser("LatestNews", detail.get(0).getLatestNews().toString());
                pref.setUser("CategoryName", detail.get(0).getCategoryName().toString());
                pref.setUser("UpdateProfile", detail.get(0).getUpdateProfile().toString());
                pref.setUser("TermsConditions", detail.get(0).getTermsConditions().toString());
                pref.setUser("TitleTerms", detail.get(0).getTitleTerms().toString());
                pref.setUser("Saving", detail.get(0).getSaving().toString());
                pref.setUser("YouMustAcceptTerms", detail.get(0).getYouMustAcceptTerms().toString());
                pref.setUser("UsernameCannotBeLess", detail.get(0).getUsernameCannotBeLess().toString());
                pref.setUser("QueryCannotBeLess", detail.get(0).getQueryCannotBeLess().toString());
                pref.setUser("PasswordCannotBeLess", detail.get(0).getPasswordCannotBeLess().toString());
                pref.setUser("LoggingIn", detail.get(0).getLoggingIn().toString());
                pref.setUser("Updating", detail.get(0).getUpdating().toString());
                pref.setUser("About", detail.get(0).getNameAbout().toString());
                pref.setUser("AppNameText", detail.get(0).getApp_name_text().toString());
                pref.setUser("Description", detail.get(0).getDescription().toString());
                pref.setUser("Website", detail.get(0).getWebsite().toString());
                pref.setUser("Emai", detail.get(0).getEmai().toString());
                pref.setUser("CategoriTitle", detail.get(0).getCategoriTitle().toString());
                pref.setUser("Date", detail.get(0).getDate().toString());
                pref.setUser("Title", detail.get(0).getTitle().toString());
                pref.setUser("InternetError", detail.get(0).getInternetError().toString());
                pref.setUser("EnableNightMode", detail.get(0).getEnable_dark_mode().toString());
                pref.setUser("Night_Mode", detail.get(0).getNight_mode().toString());
                pref.setUser("EnableCardviewLayout", detail.get(0).getEnable_cardview_layout().toString());
                pref.setUser("CardviewLayout", detail.get(0).getCardview_layout().toString());
                pref.setUser("EnableSavingData", detail.get(0).getEnable_saving_data().toString());
                pref.setUser("SavingData", detail.get(0).getSaving_data().toString());
                //Translations
            }

            @Override
            public void onFailure(retrofit2.Call<Settings> call, Throwable t) {
                if (t.toString().contains("SocketTimeoutException")) {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    Functions.Message(getApplicationContext(), getResources().getString(R.string.timeout_error));
                    pb_settings.setVisibility(View.GONE);
                } else {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    // Functions.Message(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                    pb_settings.setVisibility(View.GONE);
                }
            }
        });

    }

}
