package com.example.sportacademy1.fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.widget.Toast;

import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.API.apiClient;

import com.example.sportacademy1.MainActivity;
import com.example.sportacademy1.MapsActivity;
import com.example.sportacademy1.R;
import com.example.sportacademy1.SessionManager;
import com.example.sportacademy1.model.users.Users;
import com.example.sportacademy1.model.users.UsersData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homeFragment extends Fragment {
    TextView tvUsername, tvIdHome;
    SessionManager sessionManager;
    apiInterface apiInterface;
    String username;
    Button btnMaps;
    ProgressBar pbData;

    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        sessionManager = new SessionManager(getActivity());

        tvUsername = (TextView) v.findViewById(R.id.usernameHome);
        tvIdHome = (TextView) v.findViewById(R.id.idHome);
        pbData = (ProgressBar) v.findViewById(R.id.progresBar);
        btnMaps = (Button) v.findViewById(R.id.btnMaps);

        Users(sessionManager.getUserDetail().get(SessionManager.USER_ID));
        //id = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        //tvIdHome.setText(id);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsActivity = new Intent(getActivity(), MapsActivity.class);
                startActivity(mapsActivity);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void Users(String ids) {
        pbData.setVisibility(View.VISIBLE);

        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Users> usersCall = apiInterface.readUserResponse(ids);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if( response.body()!=null && response.isSuccessful() && response.body().isStatus() ) {

                    //Toast.makeText(getActivity(),"id ::"+ ids, Toast.LENGTH_SHORT).show();
                    UsersData usersData = response.body().getUsersData();
                    username = usersData.getUsername();

                    tvUsername.setText(username);
                    pbData.setVisibility(View.INVISIBLE);
//                    Toast.makeText(getActivity(), username, Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                pbData.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}