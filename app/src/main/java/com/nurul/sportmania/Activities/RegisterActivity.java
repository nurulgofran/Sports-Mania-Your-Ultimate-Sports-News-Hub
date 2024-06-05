package com.nurul.sportmania.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.nurul.sportmania.Helpers.ApiClient;
import com.nurul.sportmania.Helpers.NewsInterface;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.Models.Users;
import com.nurul.sportmania.R;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private Button btn_back_to_register, btn_register;
    private EditText et_username,et_email,et_password;
    private ProgressDialog pDialog;
    private SharedPref pref;
    private CheckBox chkTerms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new SharedPref(this);
        if(pref.loadNightModeState() == true){
            setTheme(R.style.AppThemeNight);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(pref.loadUser("TitleRegister"));

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btn_back_to_register    = (Button) findViewById(R.id.btnLinkToLoginScreen);
        btn_back_to_register.setText(pref.loadUser("BtnLinkToRegister"));
        btn_register            = (Button) findViewById(R.id.btnRegister);
        btn_register.setText(pref.loadUser("BtnRegister"));
        chkTerms            = (CheckBox) findViewById(R.id.chkTerms);
        chkTerms.setText(pref.loadUser("TermsConditions"));

        et_username      = (EditText) findViewById(R.id.et_reg_name);
        et_password      = (EditText) findViewById(R.id.et_reg_password);
        et_email         = (EditText) findViewById(R.id.et_reg_email);

        et_username.setHint(pref.loadUser("HintName"));
        et_password.setHint(pref.loadUser("HintPassword"));
        et_email.setHint(pref.loadUser("HintEmail"));



        chkTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username     = et_username.getText().toString().trim();
                String email        = et_email.getText().toString().trim();
                String password     = et_password.getText().toString().trim();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    // login user
                    if(isValidEmail(email)){
                        if (password.isEmpty() || password.length() < 6) {
                            Toast.makeText(getApplicationContext(), pref.loadUser("PasswordCannotBeLess"), Toast.LENGTH_LONG)
                                    .show();
                        }else{
                            if (username.isEmpty() || username.length() < 6) {
                                Toast.makeText(getApplicationContext(), pref.loadUser("UsernameCannotBeLess"), Toast.LENGTH_LONG)
                                        .show();
                            }else{

                                Boolean check = chkTerms.isChecked();

                                if(check){
                                    addUser(username, email, password, pref.loadUser("AppToken"));
                                }else{
                                    Toast.makeText(getApplicationContext(), pref.loadUser("YouMustAcceptTerms"), Toast.LENGTH_LONG)
                                            .show();
                                }


                            }
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),
                                pref.loadUser("ValidEmailAdres"), Toast.LENGTH_LONG)
                                .show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(),
                            pref.loadUser("PleaseFillCredentials"), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btn_back_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addUser(final String u_name, final String u_email, final String u_pass, String u_token){

        pDialog.setMessage(pref.loadUser("Saving"));
        showDialog();

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<Users> call = apiService.registerUser(0+"",u_name,u_email,u_pass,u_token);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(retrofit2.Call<Users> call, Response<Users> response) {

                Users c = response.body();
                List<Users.User> aa = c.getUser();
                int status       = aa.get(0).getStatus();
                String message   = aa.get(0).getMessage();
                hideDialog();
                if(status == 1){
                    Toast.makeText(getApplicationContext(),pref.loadUser("Succesfull"),Toast.LENGTH_LONG).show();
                    pref.setUser("Username", u_name);
                    pref.setUser("Email", u_email);
                    pref.setUser("Password", u_pass);
                    onBackPressed();
                }else{
                    Toast.makeText(getApplicationContext(),pref.loadUser("Error"),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<Users> call, Throwable t) {
                Log.d("Error",t.getMessage());
                Toast.makeText(getApplicationContext(),pref.loadUser("Error"),Toast.LENGTH_LONG).show();
            }
        });

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

    public  void OpenApp(){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}
