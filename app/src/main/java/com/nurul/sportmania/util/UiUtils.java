package com.nurul.sportmania.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.nurul.sportmania.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public final class UiUtils {
    private UiUtils() {
    }

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
        Picasso.get()
                .cancelRequest(iv);
        Picasso.get()
                .load(R.drawable.ic_saving_data)
                .placeholder((int) R.drawable.ic_saving_data).error((int) R.drawable.ic_saving_data)
                .into(iv);
    }

    public static void LoadDaySavingImage(ImageView iv, final Context context){
        Picasso.get()
                .cancelRequest(iv);
        Picasso.get()
                .load(R.drawable.ic_saving_data_dark)
                .placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark)
                .into(iv);
    }

    public static void LoadNightImage(final ImageView iv, final String imageUrl, final Context context){
        Picasso.get().cancelRequest(iv);
        Picasso.get().load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE).into(iv, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() { }
            @Override
            public void onError(Exception e) {
                //Try again online if cache failed
                Picasso.get().load(imageUrl).error(R.drawable.ic_saving_data).into(iv, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() { }

                    @Override
                    public void onError(Exception exception) {
                        Log.v("Picasso","Could not fetch image");
                    }
                });
            }
        });
    }

    public static void LoadDayImage(final ImageView iv, final String imageUrl, final Context context){
        Picasso.get().cancelRequest(iv);
        Picasso.get().load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE).into(iv, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() { }

            @Override
            public void onError(Exception e) {
                //Try again online if cache failed
                Picasso.get().load(imageUrl).error(R.drawable.ic_saving_data).into(iv, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() { }

                    @Override
                    public void onError(Exception exception) {
                        Log.v("Picasso","Could not fetch image");
                    }
                });
            }
        });
    }

    public static  void LoadGreyImage(final ImageView iv, final String imageUrl, final Context context){
        Picasso.get()
                .load(imageUrl).transform(new PicassoGrayscaleTransformation())
                .placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark)
                .into(iv);

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
