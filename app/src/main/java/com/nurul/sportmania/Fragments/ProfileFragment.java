package com.nurul.sportmania.Fragments;


import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.nurul.sportmania.Helpers.ApiClient;
import com.nurul.sportmania.Helpers.InternetConnection;
import com.nurul.sportmania.Helpers.NewsInterface;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.MainActivity;
import com.nurul.sportmania.Models.Users;
import com.nurul.sportmania.R;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements FragmentInterface {


    private SharedPref pref;
    private View view;
    private TextView textView;
    private ProgressDialog pDialog;
    EditText et_username,et_email,et_password;
    Button btnLogOut,btnUpdateProfile;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_profile, container, false);

        // Progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        pref = new SharedPref(getActivity());
        textView = (TextView) view.findViewById(R.id.login);
        btnLogOut = (Button) view.findViewById(R.id.btnLogOut);
        btnLogOut.setText(pref.loadUser("BtnLogout"));
        btnUpdateProfile = (Button) view.findViewById(R.id.btnUpdateProfile);
        btnUpdateProfile.setText(pref.loadUser("UpdateProfile"));

        et_username      = (EditText) view.findViewById(R.id.et_reg_name);
        et_username.setHint(pref.loadUser("HintName"));
        et_email         = (EditText) view.findViewById(R.id.et_reg_email);
        et_email.setHint(pref.loadUser("HintEmail"));
        et_password      = (EditText) view.findViewById(R.id.et_reg_password);
        et_password.setHint(pref.loadUser("HintPassword"));

        et_email.setFocusable(false);
        et_email.setEnabled(false);

        et_username.setText(pref.loadUser("Username"));
        et_email.setText(pref.loadUser("Email"));
        et_password.setText(pref.loadUser("Password"));

        if(pref.loadLoginState()){
            textView.setText(R.string.logging_in);

            btnLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pref.setLoginState(false);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });

            btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username     = et_username.getText().toString().trim();
                    String email        = et_email.getText().toString().trim();
                    String password     = et_password.getText().toString().trim();

                    if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                        // login user
                        if(isValidEmail(email)){
                            if (password.isEmpty() || password.length() < 6) {
                                Toast.makeText(getActivity(),pref.loadUser("PasswordCannotBeLess"), Toast.LENGTH_LONG)
                                        .show();
                            }else{
                                if (username.isEmpty() || username.length() < 6) {
                                    Toast.makeText(getActivity(),pref.loadUser("UsernameCannotBeLess"), Toast.LENGTH_LONG)
                                            .show();
                                }else{

                                    if (InternetConnection.checkConnection(getActivity())) {
                                        updateUser(pref.loadUser("Id"),username, email, password, pref.loadUser("AppToken"));
                                    }else{
                                        Toast.makeText(getActivity(), pref.loadUser("InternetError"), Toast.LENGTH_LONG).show();
                                    }


                                }
                            }
                        }else{
                            Toast.makeText(getActivity(),
                                    pref.loadUser("ValidEmailAdres"), Toast.LENGTH_LONG)
                                    .show();
                        }

                    } else {
                        // Prompt user to enter credentials
                        Log.d("checkLogin",getString(R.string.please_fill_credentials) + username + "--"+ email + "--"+ password + "--");
                        Toast.makeText(getActivity(),
                                pref.loadUser("PleaseFillCredentials"), Toast.LENGTH_LONG)
                                .show();
                    }

                }
            });

        }else{
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

        return view;
    }


    private void updateUser(final String id, final String u_name, final String u_email, final String u_pass, String u_token){

        pDialog.setMessage(pref.loadUser("Updating"));
        showDialog();

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<Users> call = apiService.updateUser(id,u_name,u_email,u_pass,u_token);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(retrofit2.Call<Users> call, Response<Users> response) {

                Users c = response.body();
                List<Users.User> aa = c.getUser();
                int status       = aa.get(0).getStatus();
                String message   = aa.get(0).getMessage();

                pref.setUser("Username", u_name);
                pref.setUser("Email", u_email);
                pref.setUser("Password", u_pass);

                hideDialog();
                if(status == 1){
                    Toast.makeText(getActivity(),pref.loadUser("Succesfull"),Toast.LENGTH_LONG).show();
                    //onBackPressed();
                }else{
                    Toast.makeText(getActivity(),pref.loadUser("Error"),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<Users> call, Throwable t) {
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
