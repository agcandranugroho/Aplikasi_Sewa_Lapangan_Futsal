package com.example.sportacademy1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportacademy1.R;

public class masukanPin extends Fragment {
    EditText PIN1, PIN2;
    Button btnKonf;
    String pin1,pin2;
    public masukanPin() {
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
        View v = inflater.inflate(R.layout.fragment_masukan_pin,container,false);
        PIN1 = (EditText) v.findViewById(R.id.ET_PIN);
        PIN2 = (EditText) v.findViewById(R.id.ET_PIN2);
        btnKonf = (Button) v.findViewById(R.id.btnKonPin);
        
        btnKonf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin1 = PIN1.getText().toString();
                pin2 = PIN2.getText().toString();
                if(PIN1.getText().toString().equals(PIN2.getText().toString())){
                    movePembayaran();
                }else {
                    Toast.makeText(getActivity(), "PIN Tidak Sama", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    private void movePembayaran() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new pembayaranFragment());
        transaction.commit();
    }
}