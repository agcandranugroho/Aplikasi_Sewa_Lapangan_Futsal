package com.example.sportacademy1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sportacademy1.R;

public class metodePembayaranFragment extends Fragment {
    Button LinkAja, OVO, GoPay, Ewallet;

    public metodePembayaranFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_metode_pembayaran, container,false);
        LinkAja = (Button) v.findViewById(R.id.btnLinkAja);
        OVO = (Button) v.findViewById(R.id.btnOvo);
        GoPay = (Button) v.findViewById(R.id.btnGoPay);
        Ewallet = (Button) v.findViewById(R.id.btnEwalet);

        LinkAja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveMasukanPin();
            }
        });

        OVO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveMasukanPin();
            }
        });

        GoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveMasukanPin();
            }
        });
        Ewallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveMasukanPin();
            }
        });
        return v;
    }

    public void moveMasukanPin(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new masukanPin());
        transaction.commit();
    }
}