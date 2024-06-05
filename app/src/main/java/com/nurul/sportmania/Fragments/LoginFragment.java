package com.nurul.sportmania.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nurul.sportmania.Activities.RegisterActivity;
import com.nurul.sportmania.Helpers.ApiClient;
import com.nurul.sportmania.Helpers.InternetConnection;
import com.nurul.sportmania.Helpers.NewsInterface;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.MainActivity;
import com.nurul.sportmania.Models.Login;
import com.nurul.sportmania.R;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements FragmentInterface{

    private Button btnLinkToRegisterScreen, btn_login;
    private SharedPref pref;
    private EditText et_email,et_password;
    private String email,password;
    private ProgressDialog pDialog;
    private View view;
    boolean flag = false;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_login, container, false);
        pref = new SharedPref(getActivity());

        if(pref.loadLoginState()){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        // Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        btnLinkToRegisterScreen = (Button) view.findViewById(R.id.btnLinkToRegisterScreen);
        btnLinkToRegisterScreen.setText(pref.loadUser("BtnRegister"));
        btn_login               = (Button) view.findViewById(R.id.btnLogin);
        btn_login.setText(pref.loadUser("BtnLogin"));

        et_email    = (EditText) view.findViewById(R.id.et_email);
        et_email.setHint(pref.loadUser("HintEmail"));
        et_password = (EditText) view.findViewById(R.id.et_password);
        et_password.setHint(pref.loadUser("HintPassword"));

        btnLinkToRegisterScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    if(isValidEmail(email)){
                        if (InternetConnection.checkConnection(getActivity())) {
                            chechUserDetails(email,password, pref.loadUser("AppToken"));
                        }else{
                            Toast.makeText(getActivity(), pref.loadUser("InternetError"), Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getActivity(),
                                pref.loadUser("ValidEmailAdres"), Toast.LENGTH_LONG)
                                .show();
                    }

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getActivity(),
                            pref.loadUser("PleaseFillCredentials"), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        return view;
    }

    private void chechUserDetails(final String email, final String password, final String token ){

        pDialog.setMessage(pref.loadUser("LoggingIn"));
        showDialog();

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<Login> call = apiService.checkUser(email,password,token);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(retrofit2.Call<Login> call, Response<Login> response) {
                Login c = response.body();
                List<Login.User> aa = c.getUser();
                int status = aa.get(0).getStatus();
                hideDialog();
                if(status == 1){
                    pref.setLoginState(true);
                    Toast.makeText(getActivity(),pref.loadUser("Succesfull"),Toast.LENGTH_LONG).show();
                    pref.setUser("Username", aa.get(0).getName());
                    pref.setUser("Email", email);
                    pref.setUser("Id", aa.get(0).getId()+"");
                    pref.setUser("Password", password);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                }else{
                    pref.setLoginState(false);
                    Toast.makeText(getActivity(),pref.loadUser("Error"),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<Login> call, Throwable t) {
                Log.d("Error",t.getMessage());
                Toast.makeText(getActivity(),pref.loadUser("Error"),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        getActivity().finish();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
