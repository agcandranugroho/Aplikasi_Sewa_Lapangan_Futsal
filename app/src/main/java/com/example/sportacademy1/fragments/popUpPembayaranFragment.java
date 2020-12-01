package com.example.sportacademy1.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sportacademy1.R;


public class popUpPembayaranFragment extends Fragment {
TextView idPemesananPembayaran,totalBayar, maxTimeBayar, statusBayar,tvNamaLapangan;

    public popUpPembayaranFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pop_up_pembayaran, container, false);
        idPemesananPembayaran = (TextView) v.findViewById(R.id.idPemesananPembayaran);
        totalBayar = (TextView) v.findViewById(R.id.totalBayarPembayaran);
        statusBayar = (TextView) v.findViewById(R.id.statusBayar);
        tvNamaLapangan = (TextView) v.findViewById(R.id.namaLapangan);
        return v;
    }

}