package com.nurul.sportmania.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.nurul.sportmania.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import jp.wasabeef.picasso.transformations.GrayscaleTransformation;

public class Functions {

    public static String HTMLTemplateDark(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!doctype html><html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">   \n<script type=\"text/javascript\" src=\"https://platform.twitter.com/widgets.js\"></script></head>  \n\n<body> \n<div class='content'>");
        stringBuilder.append(str);
        stringBuilder.append("</div></body></html><style>a {color:#B0BEC5; } body { color: #CFD8DC; object-fit: cover; object-position: center; overflow:hidden; line-height: 150%; font-size: medium; } img{ display: block;  width: 100%;  height: auto   !important;}</style>");
        return stringBuilder.toString();
    }

    public static String HTMLTemplateLight(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!doctype html><html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">   \n<script type=\"text/javascript\" src=\"https://platform.twitter.com/widgets.js\"></script></head>  \n\n<body> \n<div class='content'>");
        stringBuilder.append(str);
        stringBuilder.append("</div></body></html><style>a {color:#212121; } body { color: #212121; line-height: 150%; font-size: medium; } img{ display: block;  width: 100%;  height: auto   !important;}</style>");
        return stringBuilder.toString();
    }

    public static void LoadNightSavingImage(ImageView iv, final Context context){
        Picasso.with(context)
                .cancelRequest(iv);
        Picasso.with(context)
                .load(R.drawable.ic_saving_data)
                .placeholder((int) R.drawable.ic_saving_data).error((int) R.drawable.ic_saving_data)
                .into(iv);
    }

    public static void LoadDaySavingImage(ImageView iv, final Context context){
        Picasso.with(context)
                .cancelRequest(iv);
        Picasso.with(context)
                .load(R.drawable.ic_saving_data_dark)
                .placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark)
                .into(iv);
    }

    public static void LoadNightImage(final ImageView iv, final String imageUrl, final Context context){
        Picasso.with(context).cancelRequest(iv);
        Picasso.with(context).load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE).into(iv, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() { }
            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(context).load(imageUrl).error(R.drawable.ic_saving_data).into(iv, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() { }

                    @Override
                    public void onError() {
                        Log.v("Picasso","Could not fetch image");
                    }
                });
            }
        });
    }

    public static void LoadDayImage(final ImageView iv, final String imageUrl, final Context context){
        Picasso.with(context).cancelRequest(iv);
        Picasso.with(context).load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE).into(iv, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() { }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(context).load(imageUrl).error(R.drawable.ic_saving_data).into(iv, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() { }

                    @Override
                    public void onError() {
                        Log.v("Picasso","Could not fetch image");
                    }
                });
            }
        });
    }

    public static  void LoadGreyImage(final ImageView iv, final String imageUrl, final Context context){
        Picasso.with(context)
                .load(imageUrl).transform(new GrayscaleTransformation())
                .placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark)
                .into(iv);

    }

    /** CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
            //Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    public static void Message(Context context, String text){
        Log.d("MESSAGE",text);
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void Write(String text, String tag){
        Log.d(tag,text);
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void showDialog(ProgressDialog pDialog) {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hideDialog(ProgressDialog pDialog) {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}