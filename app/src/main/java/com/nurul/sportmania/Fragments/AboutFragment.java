package com.nurul.sportmania.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.nurul.sportmania.Helpers.Functions;
import com.nurul.sportmania.Helpers.InternetConnection;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements FragmentInterface {

    TextView wb_description_text, app_website_text, app_email_text, app_name_text;
    TextView app_name,app_email,app_website;
    WebView wb_description;
    View view;
    SharedPref pref;
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_about, container, false);
        pref = new SharedPref(getActivity());
        //Translations
        app_name_text = (TextView) view.findViewById(R.id.app_name_text);
        app_name_text.setText(pref.loadUser("AppNameText"));
        app_email_text = (TextView) view.findViewById(R.id.app_email_text);
        app_email_text.setText(pref.loadUser("Emai"));
        app_website_text = (TextView) view.findViewById(R.id.app_website_text);
        app_website_text.setText(pref.loadUser("Website"));
        wb_description_text = (TextView) view.findViewById(R.id.wb_description_text);
        wb_description_text.setText(pref.loadUser("Description"));
        //Translations
        app_name = (TextView) view.findViewById(R.id.app_name);
        app_email = (TextView) view.findViewById(R.id.app_email);
        app_website = (TextView) view.findViewById(R.id.app_website);
        wb_description = (WebView) view.findViewById(R.id.wb_description);
        wb_description.getSettings();
        wb_description.setBackgroundColor(Color.TRANSPARENT);

        WebSettings webSettings = wb_description.getSettings();

        if (getResources ().getBoolean (R.bool.isTablet)) {
            webSettings.setTextZoom (125); //large font for tablet
        } else {
            webSettings.setTextZoom (100); //default for phone
        }

        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: #7E8889;}"
                + "</style></head>"
                + "<body>" +pref.loadUser("AppDescription")+ "</body></html>";


        if (InternetConnection.checkConnection(getActivity())) {

        }else{
            Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_LONG).show();
        }


        app_name.setText(pref.loadUser("AppName"));
        app_email.setText(pref.loadUser("AppEmail"));
        app_website.setText(pref.loadUser("AppWebsite"));
        //wb_description.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
        if (pref.loadNightModeState()){
            wb_description.loadData(Functions.HTMLTemplateDark(pref.loadUser("AppDescription")), "text/html; charset=utf-8", "utf-8");
        }else{
            wb_description.loadData(Functions.HTMLTemplateLight(pref.loadUser("AppDescription")), "text/html; charset=utf-8", "utf-8");
        }

        return view;
    }

    @Override
    public void onBackPressed() {
        getActivity().finish();
    }

}
