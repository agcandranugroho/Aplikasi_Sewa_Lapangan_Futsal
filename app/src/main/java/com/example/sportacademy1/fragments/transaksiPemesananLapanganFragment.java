package com.example.sportacademy1.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.R;
import com.example.sportacademy1.SessionManager;
import com.example.sportacademy1.model.pemesanan.Pemesanan;
import com.example.sportacademy1.model.pemesanan.PemesananData;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class transaksiPemesananLapanganFragment extends Fragment {
    Spinner spinnerLapangan,spinnerJamMulai;
    TextView tvIdPelanggan,tvIdLapangan,tvHarga, tvHargaCek;
    EditText etTanggalMain,etJamMulai,etJamSelesai,tvJamMulai,tvJamSelesai;
    Button btnSavePesan, btnHitung;

    apiInterface apiInterface;
    SessionManager sessionManager;

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;

    //LAYOUT FRAGMENT_PEMBAYARAN
    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    TextView tvId, tvHargaPopUp, tvStatus, tvNamaLapangan;
    String id_pemesananPopUp,hargaPopUp,statusPopUP,namaLapanganPopUp;


    String id_pemesanan, id_pelanggan,id_lapangan, tanggal_main, durasi_start, durasi_end, harga;
    String angka1, angka2;
    Integer SET1,SET2;

    Boolean isDataValid;

    private String [] lapangan = {"Pilih Lapangan","Lapangan 1","Lapangan 2"};

    public transaksiPemesananLapanganFragment() {
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
        View v = inflater.inflate(R.layout.fragment_transaksi_pemesanan_lapangan,container,false);
        sessionManager = new SessionManager(getActivity());
        spinnerLapangan = (Spinner) v.findViewById(R.id.spinnerTransaksi);


        btnSavePesan = (Button) v.findViewById(R.id.btnSavePesan);
        btnHitung = (Button) v.findViewById(R.id.btnHitung);

        etTanggalMain = (EditText) v.findViewById(R.id.etTanggalMain);
        etJamMulai = (EditText) v.findViewById(R.id.etWaktuMulai);
        etJamSelesai = (EditText) v.findViewById(R.id.etJamSelesai);
        tvJamMulai = (EditText) v.findViewById(R.id.tvJamMulai);
        tvJamSelesai = (EditText) v.findViewById(R.id.tvJamSelesai);

        tvHarga = (TextView) v.findViewById(R.id.hargaTransaksi);
        tvIdLapangan = (TextView) v.findViewById(R.id.idLapangan);
        tvIdPelanggan = (TextView) v.findViewById(R.id.idPelangganPesan);
        tvHargaCek = (TextView) v.findViewById(R.id.hargaTransaksiCek);

        id_pelanggan = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        tvIdPelanggan.setText(id_pelanggan);

        spinnerLapangan();

        //BUTTON HITUNG
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etJamMulai.getText().toString().equals("")) {
                    etJamMulai.setError("Tidak Boleh Kosong");
                }else if(etJamSelesai.getText().toString().equals("")){
                    etJamSelesai.setError(("Tidak Boleh Kosong"));
                }else if(SET1>SET2){
                    Toast.makeText(getActivity(), "Periksan Lagi Jam Mulai dan Selesai Anda", Toast.LENGTH_SHORT).show();
                }else if(SET1==SET2){
                    Toast.makeText(getActivity(), "Jam Tidak Boleh Sama", Toast.LENGTH_SHORT).show();
                }else{
                    hitung();
                }
            }
        });

        //event klik edit text tanggal main
        etTanggalMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(); 
            }
        });

        //event klik edit text jam mulai main
        etJamMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeStart();
            }
        });

        //event klik edit text jam selesai main
        etJamSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeEnd();
            }
        });

        //button save pesanan
        btnSavePesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               id_pelanggan = tvIdPelanggan.getText().toString();
               id_lapangan = tvIdLapangan.getText().toString();
               tanggal_main = etTanggalMain.getText().toString();
               durasi_start = tvJamMulai.getText().toString();
               durasi_end = tvJamSelesai.getText().toString();
               harga = tvHarga.getText().toString();

               if(id_lapangan.trim().equals("0")){
                   Toast.makeText(getActivity(), "Pilih Lapangan", Toast.LENGTH_SHORT).show();
               }else if(etTanggalMain.getText().toString().equals("")) {
                   etTanggalMain.setError("Tidak Boleh Kosong");
               }
               else if(etJamMulai.getText().toString().equals("")) {
                    etJamMulai.setError("Tidak Boleh Kosong");
                }else if(etJamSelesai.getText().toString().equals("")){
                    etJamSelesai.setError(("Tidak Boleh Kosong"));
                }else if(SET1>SET2){
                    Toast.makeText(getActivity(), "Periksan Lagi Jam Mulai dan Selesai Anda", Toast.LENGTH_SHORT).show();
                }else if(SET1==SET2){
                    Toast.makeText(getActivity(), "Jam Tidak Boleh Sama", Toast.LENGTH_SHORT).show();
               }
               // VALIDASI WAKTU OPEN
               else if(durasi_start.trim().equals("21") || durasi_start.trim().equals("22")
                      ||durasi_start.trim().equals("23") || durasi_start.trim().equals("00")
                      ||durasi_start.trim().equals("1") || durasi_start.trim().equals("2")
                      ||durasi_start.trim().equals("3") || durasi_start.trim().equals("4")
                      ||durasi_start.trim().equals("5") || durasi_start.trim().equals("6"))
               {
                   etJamMulai.setError("Sudah Tutup");
                   Toast.makeText(getActivity(), "Tutup dari jam 21:00-07:00", Toast.LENGTH_SHORT).show();
               }
               else if(durasi_end.trim().equals("21") || durasi_end.trim().equals("22")
                       ||durasi_end.trim().equals("23") || durasi_end.trim().equals("00")
                       ||durasi_end.trim().equals("1") || durasi_end.trim().equals("2")
                       ||durasi_end.trim().equals("3") || durasi_end.trim().equals("4")
                       ||durasi_end.trim().equals("5") || durasi_end.trim().equals("6"))
               {
                   etJamSelesai.setError("Sudah Tutup");
                   Toast.makeText(getActivity(), "Tutup dari jam 21:00-07:00", Toast.LENGTH_SHORT).show();
               }else {
                   hitungCek();
                   if(tvHargaCek.getText().toString().equals(tvHarga.getText().toString())){
                       new AlertDialog.Builder(getActivity())
                               .setIcon(R.mipmap.ic_launcher)
                               .setMessage("Apakah Sampean Yakin Untuk Simpan?")
                               .setPositiveButton("IYA", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       savePesan();
                                   }
                               }).setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                   }else {
                       Toast.makeText(getActivity(), "Klik Tombol Hitung Dulu Ya", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });

        return v;
    }

    private void savePesan() {
        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Pemesanan> pemesananCall = apiInterface.pemesananResponse(id_pelanggan,id_lapangan,tanggal_main,durasi_start,durasi_end,harga);
        pemesananCall.enqueue(new Callback<Pemesanan>() {
            @Override
            public void onResponse(Call<Pemesanan> call, Response<Pemesanan> response) {
                if (response.body()!=null && response.isSuccessful() && response.body().isStatus()){
                    //Toast.makeText(getActivity(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    //FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    //transaction.replace(R.id.container, new pembayaranFragment());
                    //transaction.commit();
                    popUpPemesanan();
                    //Toast.makeText(getActivity(),"id"+id_pelanggan,Toast.LENGTH_SHORT).show();
                }else {
                    //new AlertDialog.Builder(getActivity())
                           // .setIcon(R.mipmap.ic_launcher)
                           // .setMessage("Pemesanan Lapangan Anda yang Belum Dibayar tidak Boleh Lebih Dari 2, " +
                                   // "silahkan lakukan pembayaran terlebih dahulu").show();
                    Toast.makeText(getActivity(),response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pemesanan> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Method Pilih Lapangan
    public void spinnerLapangan(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,lapangan);
        spinnerLapangan.setAdapter(adapter);

        spinnerLapangan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        tvIdLapangan.setText("0");
                        break;
                    case 1:
                        tvIdLapangan.setText("1");
                        break;
                    case 2:
                        tvIdLapangan.setText("2");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    //Method Input Tanggal Main
    private void showDateDialog() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                //id_pemesanan = dateFormat.format(newDate.getTime());
                etTanggalMain.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    //method input jam mulai main
    private void showTimeStart() {
        Calendar timeStart = Calendar.getInstance();
        int jam = timeStart.get(Calendar.HOUR_OF_DAY);
        int menit = timeStart.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                etJamMulai.setText(hourOfDay + ":" + "00");
                SET1 = hourOfDay;
                tvJamMulai.setText(String.valueOf(SET1));
            }
        },jam,menit,true);//24 jam format
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    //method input jam selesai main
    public void showTimeEnd() {
        Calendar timeEnd = Calendar.getInstance();
        int jam = timeEnd.get(Calendar.HOUR_OF_DAY);
        int menit = timeEnd.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                etJamSelesai.setText(hourOfDay + ":" +"00");
                SET2 = hourOfDay;
                tvJamSelesai.setText(String.valueOf(SET2));
            }
        },jam,menit,true); // format 24 jam
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void popUpPemesanan(){
        dialogBuilder = new AlertDialog.Builder(getActivity());
        final View popUpView = getLayoutInflater().inflate(R.layout.fragment_pop_up_pembayaran,null);

        tvId = (TextView) popUpView.findViewById(R.id.idPemesananPembayaran);
        tvHargaPopUp = (TextView) popUpView.findViewById(R.id.totalBayarPembayaran);
        tvStatus = (TextView) popUpView.findViewById(R.id.statusBayar);
        tvNamaLapangan = (TextView)  popUpView.findViewById(R.id.namaLapangan);

        apiInterface = apiClient.getClient().create(apiInterface.class);
        Call<Pemesanan> callPembayaran = apiInterface.pemesananRead();
        callPembayaran.enqueue(new Callback<Pemesanan>() {
            @Override
            public void onResponse(Call<Pemesanan> call, Response<Pemesanan> response) {
                if (response.body()!=null && response.isSuccessful() && response.body().isStatus()){
                    PemesananData pemesananData = response.body().getPemesananData();
                    id_pemesananPopUp = pemesananData.getIdPemesanan();
                    hargaPopUp = pemesananData.getHarga();
                    statusPopUP = pemesananData.getStatusPembayaran();
                    namaLapanganPopUp = pemesananData.getNamaLapangan();

                    tvId.setText(id_pemesananPopUp);
                    tvHargaPopUp.setText("Rp. "+hargaPopUp);
                    tvStatus.setText(statusPopUP);
                    tvNamaLapangan.setText(namaLapanganPopUp);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.container,new recyclerViewDaftarPemesananFragment0());
                    transaction.commit();

                    //Toast.makeText(getActivity(),"Berhasil, Lanjutkan pembayaran",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"data tidak tampil",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pemesanan> call, Throwable t) {

            }
        });
        dialogBuilder.setView(popUpView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public void hitung(){
        angka1=tvJamMulai.getText().toString();
        angka2 = tvJamSelesai.getText().toString();
        Integer ANGKA1 = Integer.parseInt(angka1);
        Integer ANGKA2 = Integer.parseInt(angka2);
        //Integer ANGKA2 = Integer.valueOf(String.valueOf((tvJamSelesai.getText())));
        Integer HARGA = (ANGKA2-ANGKA1)*50000;
        tvHarga.setText(String.valueOf(HARGA));
    }
    public void hitungCek(){
        angka1=tvJamMulai.getText().toString();
        angka2 = tvJamSelesai.getText().toString();
        Integer ANGKA1 = Integer.parseInt(angka1);
        Integer ANGKA2 = Integer.parseInt(angka2);
        //Integer ANGKA2 = Integer.valueOf(String.valueOf((tvJamSelesai.getText())));
        Integer HARGA = (ANGKA2-ANGKA1)*50000;
        tvHargaCek.setText(String.valueOf(HARGA));
    }
}