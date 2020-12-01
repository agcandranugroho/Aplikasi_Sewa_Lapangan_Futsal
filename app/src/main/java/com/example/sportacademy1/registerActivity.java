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
import com.example.sportacademy1.model.register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etusername, etemail, etpassword, etNama, etNoTelp;
    Button btnRegister;
    TextView tvAlreadyHaveAccount;
    apiInterface apiInterface;


    String username, email, password, nama, no_telp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNama = findViewById(R.id.namaRegister);
        etNoTelp = findViewById(R.id.noTelpRegister);
        etusername = findViewById(R.id.usernameRegister);
        etpassword = findViewById(R.id.passwordRegister);
        etemail = findViewById(R.id.emailRegister);

        btnRegister = findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(this);

        tvAlreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);
        tvAlreadyHaveAccount.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonRegister:
                username = etusername.getText().toString();
                password = etpassword.getText().toString();
                email = etemail.getText().toString();
                nama = etNama.getText().toString();
                no_telp = etNoTelp.getText().toString();
                //PARAMETER DI METHOD REGISTERNYA HARUS SAMA URUTANNYA DENGAN @FIELD YANG ADA DI CLASS APIINTERFACE
                if(nama.trim().equals("")){
                etNama.setError("ini tidak boleh kosong");
                }
                else if (username.trim().equals("21")){
                    etusername.setError("ini tidak boleh kosong");
                }
                else if(password.trim().equals("")){
                    etpassword.setError("ini tidak boleh kosong");
                }
                else if(email.trim().equals("")) {
                    etemail.setError("ini tidak boleh kosong");
                }
                else if(no_telp.trim().equals("")){
                    etNoTelp.setError("ini tidak boleh kosong");
                }
                else {
                    register(username,nama,email, no_telp,password);
                }

                break;
            case R.id.tvAlreadyHaveAccount:
                Intent intent = new Intent(this, loginActivity.class);
                startActivity(intent);
                finish();
                //register(username, password, email);
                break;
        }
    }

    private void register(String username, String nama,String email, String no_telp, String password) {
        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Register> call = apiInterface.registerResponse(username,nama,email,no_telp,password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body()!=null && response.isSuccessful() && response.body().isStatus()) {
                    Toast.makeText(registerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity.this, loginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(registerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(registerActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}