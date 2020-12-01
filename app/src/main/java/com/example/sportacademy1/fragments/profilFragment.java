package com.example.sportacademy1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.MainActivity;
import com.example.sportacademy1.R;
import com.example.sportacademy1.SessionManager;
import com.example.sportacademy1.loginActivity;
import com.example.sportacademy1.model.login.Login;
import com.example.sportacademy1.model.login.LoginData;
import com.example.sportacademy1.model.users.Users;
import com.example.sportacademy1.model.users.UsersData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profilFragment extends Fragment {
    TextView tvUsername;
    EditText etEmail, tvNoTelp, tvUsername2,tvNama;
    Button btnLogout;
    ImageButton btnEditProfil;
    SessionManager sessionManager;
    apiInterface apiInterface;
    String username, nama, email,no_telp,id;

    ProgressBar pbBar;

    public profilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        sessionManager = new SessionManager(getActivity());
        //sessionManager.crudUsers();

        tvUsername =(TextView) v.findViewById(R.id.usernameProfil);
        etEmail =(EditText) v.findViewById(R.id.emailProfil);
        tvNama = (EditText)v.findViewById(R.id.namaProfil);
        tvNoTelp = (EditText)v.findViewById(R.id.noTelpProfil);
        tvUsername2 =(EditText) v.findViewById(R.id.username2);
        pbBar = (ProgressBar) v.findViewById(R.id.progresBarProfil);
        //tvId = (TextView) v.findViewById(R.id.idProfil);
        Users(sessionManager.getUserDetail().get(SessionManager.USER_ID));
        //v.findViewById(R.id.logoutProfil).setOnClickListener(this);
        //v.findViewById(R.id.btnEditProfil).setOnClickListener(this);

       btnLogout =(Button) v.findViewById(R.id.logoutProfil);
       btnEditProfil =(ImageButton) v.findViewById(R.id.btnEditProfil);

       btnEditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveEditProfil();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveLoginActivity();
            }
        });
        return v;
    }

    private void moveEditProfil() {
        //editProfilFragment editProfilFragment = new editProfilFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new editProfilFragment(username,nama,no_telp,id));
        transaction.commit();
    }

    private void moveLoginActivity() {
        sessionManager.logOutSession();
        Intent intent = new Intent(getActivity(), loginActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
    private void Users(String ids) {
        pbBar.setVisibility(View.VISIBLE);

        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Users> userCall = apiInterface.readUserResponse(ids);
        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if( response.body()!=null && response.isSuccessful() && response.body().isStatus() ) {

                    UsersData usersData = response.body().getUsersData();
                    username = usersData.getUsername();
                    email = usersData.getEmail();
                    nama = usersData.getNama();
                    no_telp = usersData.getNoTelp();
                    id = sessionManager.getUserDetail().get(SessionManager.USER_ID);

                    tvUsername.setText(username);
                    etEmail.setText(email);
                    tvNama.setText(nama);
                    tvNoTelp.setText(no_telp);
                    tvUsername2.setText(username);

                    pbBar.setVisibility(View.INVISIBLE);
                    //tvId.setText(id);
                    //Toast.makeText(getActivity(), username, Toast.LENGTH_SHORT).show();
                } else {

                   Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        }
}