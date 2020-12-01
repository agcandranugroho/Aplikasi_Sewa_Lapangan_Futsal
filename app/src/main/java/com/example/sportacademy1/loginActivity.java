package com.example.sportacademy1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.model.login.Login;
import com.example.sportacademy1.model.login.LoginData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etusername, etpassword;
    Button btnLogin;
    TextView tvCreateNewAccount;
    String username, password,id;
    apiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etusername = findViewById(R.id.usernameLogin);
        etpassword = findViewById(R.id.passwordLogin);

        btnLogin = findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(this);

        tvCreateNewAccount = findViewById(R.id.tvCreateNewAccount);
        tvCreateNewAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                username = etusername.getText().toString();
                password = etpassword.getText().toString();
                if (username.trim().equals("")){
                    etusername.setError("username salah atau username kosong");
                }
                else if(password.trim().equals("")){
                    etpassword.setError("password salah atau password tidak boleh kosong");
                }
                else {
                    login(id,username,password);
                }

                break;
            case R.id.tvCreateNewAccount:
                Intent intent = new Intent(this, registerActivity.class);
                startActivity(intent);
        }
    }

    private void login(String id,String username, String password) {
        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(id,username, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body()!=null && response.isSuccessful() && response.body().isStatus()) {

                    //session
                    sessionManager = new SessionManager(loginActivity.this);
                    LoginData loginData = response.body().getLoginData();
                    sessionManager.createLoginSession(loginData);

                    //Toast.makeText(loginActivity.this, response.body().getLoginData().getUsername(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(loginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(loginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}