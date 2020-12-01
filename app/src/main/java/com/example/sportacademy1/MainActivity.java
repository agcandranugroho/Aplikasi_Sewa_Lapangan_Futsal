package com.example.sportacademy1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sportacademy1.fragments.homeFragment;
import com.example.sportacademy1.fragments.metodePembayaranFragment;
import com.example.sportacademy1.fragments.profilFragment;
import com.example.sportacademy1.fragments.recyclerViewDaftarPemesananFragment0;
import com.example.sportacademy1.fragments.recyclerViewJadwalMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView tvusername;
    SessionManager sessionManager;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(MainActivity.this);
        if (!sessionManager.isLogged()) {
            moveToLogin();
        }
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomMethod);

        //tvusername = findViewById(R.id.username1);
        //username = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        //tvusername.setText(username);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new homeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment=null;
                    switch (item.getItemId()) {
                        case R.id.Home:
                            fragment=new homeFragment();
                            break;
                        case R.id.profilHome:
                            fragment=new profilFragment();
                            break;
                        case R.id.jadwalManiHome:
                            fragment=new recyclerViewJadwalMain();
                            break;
                        case R.id.transaksiHome:
                            fragment=new recyclerViewDaftarPemesananFragment0();
                            break;
                        case R.id.pembayaranHome:
                            fragment=new metodePembayaranFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }
            };
    private void moveToLogin() {
        Intent intent= new Intent(MainActivity.this, loginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}