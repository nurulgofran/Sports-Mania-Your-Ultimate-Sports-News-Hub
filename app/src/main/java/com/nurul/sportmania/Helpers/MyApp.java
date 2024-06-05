package com.nurul.sportmania.Helpers;

import android.app.Application;

import com.nurul.sportmania.R;
import com.google.android.gms.ads.MobileAds;
import com.onesignal.OneSignal;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MobileAds.initialize(this, getResources().getString(R.string.admob_ad_id));

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
