package com.example.sportacademy1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.model.users.Users;

import com.example.sportacademy1.R;
import com.example.sportacademy1.SessionManager;
import com.example.sportacademy1.model.users.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class editProfilFragment extends Fragment {

    TextView tvIdEdit;
    EditText etNamaEdit, etUsernameEdit, etNoTelpEdit;
    Button btnSaveEditProfil;
    SessionManager sessionManager;
    String  username, nama, no_telp;
    String id;
    apiInterface apiInterface;

    public editProfilFragment() {
        // Required empty public constructor
    }
    public editProfilFragment(String usr, String nama, String telp, String id ) {
        this.id = id;
        this.username = usr;
        this.nama = nama;
        this.no_telp = telp;
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

        View v = inflater.inflate(R.layout.fragment_edit_profil, container, false);
        sessionManager = new SessionManager(getActivity());

        tvIdEdit = (TextView) v.findViewById(R.id.idUserEdit);

        etNamaEdit = (EditText) v.findViewById(R.id.namaEdit);
        etUsernameEdit = (EditText) v.findViewById(R.id.usernameEdit);
        etNoTelpEdit = (EditText) v.findViewById(R.id.noTelpEdit);

        btnSaveEditProfil =(Button) v.findViewById(R.id.saveEditProfil);

        tvIdEdit.setText(id);
        etUsernameEdit.setText(username);
        etNamaEdit.setText(nama);
        etNoTelpEdit.setText(no_telp);

        btnSaveEditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id= tvIdEdit.getText().toString().trim();
                nama = etNamaEdit.getText().toString().trim();
                username = etUsernameEdit.getText().toString().trim();
                no_telp = etNoTelpEdit.getText().toString().trim();
                updateProfil(id, nama, username, no_telp);
            }
        });
        return v;
    }

    private void updateProfil(String id, String nama, String username, String no_telp){
        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Users> call = apiInterface.updateUserResponse(id,nama,username,no_telp);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.body()!=null && response.isSuccessful() && response.body().isStatus()) {
                    Toast.makeText(getActivity(), "berhasil",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(),"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getActivity(),"username sudah ada",Toast.LENGTH_SHORT).show();
            }
        });
    }
}