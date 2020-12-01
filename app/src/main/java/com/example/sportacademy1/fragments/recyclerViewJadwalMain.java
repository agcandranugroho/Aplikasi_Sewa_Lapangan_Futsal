package com.example.sportacademy1.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.Adapter.AdapterDataJadwalMain;
import com.example.sportacademy1.R;
import com.example.sportacademy1.model.lapangan.Lapangan;
import com.example.sportacademy1.model.lapangan.LapanganData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class recyclerViewJadwalMain extends Fragment {
    private RecyclerView rvJadwalMain;
    private AdapterDataJadwalMain adJadwalMain;
    private RecyclerView.LayoutManager lmJadwalMain;
    private List<LapanganData> lapanganDataList = new ArrayList<>();
    String key;
    Button buttonCari;
    EditText Etcari;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;

    apiInterface apiInterface;

    public recyclerViewJadwalMain() {
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
        View v = inflater.inflate(R.layout.fragment_recycler_view_jadwal_main, container, false);
        rvJadwalMain = (RecyclerView) v.findViewById(R.id.RV_jadwalMain);
        Etcari = (EditText) v.findViewById(R.id.cariJadwal);
        buttonCari = (Button) v.findViewById(R.id.btnCari);

        lmJadwalMain = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvJadwalMain.setLayoutManager(lmJadwalMain);
        rvJadwalMain.setHasFixedSize(true);

        Etcari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog();
            }
        });
        buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = Etcari.getText().toString();
                TampilJadwalMain();
            }
        });
        return v;
    }

    private void showDataDialog() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);

                Etcari.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void TampilJadwalMain() {
        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Lapangan> lapanganCall = apiInterface.jadwalmain(key);
        lapanganCall.enqueue(new Callback<Lapangan>() {
            @Override
            public void onResponse(Call<Lapangan> call, Response<Lapangan> response) {
                if (response.body()!=null && response.isSuccessful() && response.body().isStatus()){
                lapanganDataList = response.body().getData();
                adJadwalMain = new AdapterDataJadwalMain(getActivity(),lapanganDataList);
                rvJadwalMain.setAdapter(adJadwalMain);
                adJadwalMain.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lapangan> call, Throwable t) {

            }
        });
    }
}