package com.example.sportacademy1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.Adapter.AdapterDataDaftarPemesananLapangan0;
import com.example.sportacademy1.R;
import com.example.sportacademy1.SessionManager;
import com.example.sportacademy1.model.pembayaran.PembayaranData;
import com.example.sportacademy1.model.pembayaran.Pembayaran;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class recyclerViewDaftarPemesananFragment0 extends Fragment {

    private RecyclerView rvDataPembayaran;
    private RecyclerView.Adapter adDataPembayaran;
    private RecyclerView.LayoutManager lmDataPembayaran;
    private List<PembayaranData> pembayaranDataList = new ArrayList<>();
    private FloatingActionButton fabTambahPemesanan,fabRefresh;
    Button btnSudah, btnBelum;
    ProgressBar pbBar;
    String idUser;

    apiInterface apiInterface;
    SessionManager sessionManager;

    public recyclerViewDaftarPemesananFragment0() {
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
        View v = inflater.inflate(R.layout.fragment_recycler_view_daftar_pemesanan_0, container, false);
        sessionManager = new SessionManager(getActivity());

        btnBelum = v.findViewById(R.id.btnBelum);
        btnSudah = v.findViewById(R.id.btnSudah);

        rvDataPembayaran = (RecyclerView) v.findViewById(R.id.recyclerView);
        pbBar = (ProgressBar) v.findViewById(R.id.progresBarRCV);
        lmDataPembayaran = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvDataPembayaran.setLayoutManager(lmDataPembayaran);

        idUser = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        retrieveDataPembayaran();

        fabTambahPemesanan = (FloatingActionButton) v.findViewById(R.id.fabTambahPemesanan);
        fabRefresh = (FloatingActionButton) v.findViewById(R.id.fabRefresh);

        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new recyclerViewDaftarPemesananFragment0());
                transaction.commit();
            }
        });

        fabTambahPemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTransaksiFragment();
            }
        });
        btnSudah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transactionSudah = getFragmentManager().beginTransaction();
                transactionSudah.replace(R.id.container,new recyclerViewDafterPemesananFragment1());
                transactionSudah.commit();
            }
        });
        btnBelum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transactionBelum = getFragmentManager().beginTransaction();
                transactionBelum.replace(R.id.container, new recyclerViewDaftarPemesananFragment0());
                transactionBelum.commit();
            }
        });
        return v;
    }

    public void retrieveDataPembayaran(){
        pbBar.setVisibility(View.VISIBLE);

        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Pembayaran> pembayaranCall = apiInterface.pembayaranRead(idUser);
        pembayaranCall.enqueue(new Callback<Pembayaran>() {
            @Override
            public void onResponse(Call<Pembayaran> call, Response<Pembayaran> response) {
                if (response.body()!=null && response.isSuccessful() && response.body().isStatus()){

                    pembayaranDataList = response.body().getData();
                    adDataPembayaran = new AdapterDataDaftarPemesananLapangan0(getActivity(),pembayaranDataList);
                    rvDataPembayaran.setAdapter(adDataPembayaran);

                    adDataPembayaran.notifyDataSetChanged();
                    pbBar.setVisibility(View.INVISIBLE);
                    //Toast.makeText(getContext(),"berhasil", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    pbBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Pembayaran> call, Throwable t) {
                Toast.makeText(getActivity(), "gagal", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void moveToTransaksiFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new transaksiPemesananLapanganFragment());
        transaction.commit();
    }
}