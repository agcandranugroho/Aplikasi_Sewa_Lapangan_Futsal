package com.example.sportacademy1.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.R;
import com.example.sportacademy1.SessionManager;
import com.example.sportacademy1.model.bayar.Bayar;
import com.example.sportacademy1.model.bayar.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pembayaranFragment extends Fragment {

    EditText cariBayar,kodeBayar, kodePesan, nama, lapangan, tanggal, jamMulai, jamSelesai,totalBayar;
    Button btnCari, btnBayar;

    apiInterface apiInterface;
    SessionManager sessionManager;

    String key,id,idBayar,idPesan,namaUser,namaLapangan,tanggalMain,jamStart,jamEnd,total;
    public pembayaranFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pembayaran, container, false);
        kodeBayar = (EditText) v.findViewById(R.id.etidpembayaran);
        kodePesan = (EditText) v.findViewById(R.id.etidpesan);
        nama = (EditText) v.findViewById(R.id.etnamauser);
        lapangan = (EditText) v.findViewById(R.id.etlapangan);
        tanggal = (EditText) v.findViewById(R.id.ettanggal);
        jamMulai = (EditText) v.findViewById(R.id.etmulai);
        jamSelesai= (EditText) v.findViewById(R.id.etselesai);
        totalBayar = (EditText) v.findViewById(R.id.ettotal);
        cariBayar = (EditText) v.findViewById(R.id.kodePembayaran);

        btnBayar = (Button) v.findViewById(R.id.btnbayar);
        btnCari = (Button) v.findViewById(R.id.btnCariPembayaran);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = cariBayar.getText().toString();
                cariDataPembayaran();
            }
        });

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kodeBayar.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "data pembayaran tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setMessage("Apakah Anda Yakin Bayar Sekarang?");
                    dialog.setNeutralButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            id=kodeBayar.getText().toString();
                            updatePembayaran();
                        }
                    }); dialog.show();
                }
            }
        });

        return v;
    }

    private void updatePembayaran() {
        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Bayar> bayar= apiInterface.updateBayar(id);
        bayar.enqueue(new Callback<Bayar>() {
            @Override
            public void onResponse(Call<Bayar> call, Response<Bayar> response) {
                if(response.body()!=null && response.isSuccessful() && response.body().isStatus()) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    kodeBayar.setText("");
                    kodePesan.setText("");
                    nama.setText("");
                    lapangan.setText("");
                    tanggal.setText("");
                    jamMulai.setText("");
                    jamSelesai.setText("");
                    totalBayar.setText("");
                    cariBayar.setText("");
                }else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Bayar> call, Throwable t) {

            }
        });
    }

    public void cariDataPembayaran(){
        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Bayar> bayarCall = apiInterface.prosesPembayaran(key);
        bayarCall.enqueue(new Callback<Bayar>() {
            @Override
            public void onResponse(Call<Bayar> call, Response<Bayar> response) {
                if(response.body()!=null && response.isSuccessful() && response.body().isStatus()){
                    Data data = response.body().getData();
                    idBayar = data.getIdPembayaran();
                    idPesan = data.getIdPemesanan();
                    namaUser = data.getNama();
                    namaLapangan = data.getNamaLapangan();
                    tanggalMain = data.getTanggal();
                    jamStart = data.getJamMulai();
                    jamEnd = data.getJamSelesai();
                    total = data.getTotalBayar();

                    kodeBayar.setText(idBayar);
                    kodePesan.setText(idPesan);
                    nama.setText(namaUser);
                    lapangan.setText(namaLapangan);
                    tanggal.setText(tanggalMain);
                    jamMulai.setText(jamStart+":"+"00");
                    jamSelesai.setText(jamEnd+":"+"00");
                    totalBayar.setText("Rp"+" "+total);
                }else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    kodeBayar.setText("");
                    kodePesan.setText("");
                    nama.setText("");
                    lapangan.setText("");
                    tanggal.setText("");
                    jamMulai.setText("");
                    jamSelesai.setText("");
                    totalBayar.setText("");
                }
            }

            @Override
            public void onFailure(Call<Bayar> call, Throwable t) {
            }
        });
    }
}