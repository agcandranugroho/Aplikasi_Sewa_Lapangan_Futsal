package com.example.sportacademy1.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.API.apiClient;
import com.example.sportacademy1.R;
import com.example.sportacademy1.model.pembayaran.Pembayaran;
import com.example.sportacademy1.model.pembayaran.PembayaranData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataDaftarPemesananLapangan0 extends RecyclerView.Adapter<AdapterDataDaftarPemesananLapangan0.HolderData> {
    private Context context;
    apiInterface apiInterface;
    private List<PembayaranData> pembayaranDataList;

    public AdapterDataDaftarPemesananLapangan0(Context context, List<PembayaranData> pembayaranDataList) {
        this.context = context;
        this.pembayaranDataList = pembayaranDataList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        PembayaranData pembayaranData = pembayaranDataList.get(position);
        holder.tvIdPembayaran.setText(pembayaranData.getIdPembayaran());
        holder.tvIdPemesanan.setText(pembayaranData.getIdPemesanan());
        holder.tvStatusPembayaran.setText(pembayaranData.getStatus());
        holder.tvTotalBayar.setText("Rp. "+pembayaranData.getTotalBayar());
        holder.tvNamaLapangan.setText(pembayaranData.getNamaLapangan());
        holder.tvTanggal.setText(pembayaranData.getTanggal());
        holder.tvJamMain.setText(pembayaranData.getJamMulai()+":00"+"-"+pembayaranData.getJamSelesai()+":00");
    }

    @Override
    public int getItemCount() {
        return pembayaranDataList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvIdPembayaran,tvIdPemesanan, tvTotalBayar, tvStatusPembayaran,tvNamaLapangan,tvTanggal,tvJamMain;
        String idPemesanan;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvIdPembayaran = itemView.findViewById(R.id.id_pembayaranRCV);
            tvIdPemesanan = itemView.findViewById(R.id.id_pemesananRCV);
            tvTotalBayar = itemView.findViewById(R.id.totalHargaRCV);
            tvStatusPembayaran = itemView.findViewById(R.id.statusPembayaranRCV);
            tvNamaLapangan = itemView.findViewById(R.id.nama_lapanganRCV);
            tvTanggal = itemView.findViewById(R.id.tanggalRCV);
            tvJamMain = itemView.findViewById(R.id.jamMainRCV);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setMessage("Apakah Anda Yakin Ingin Menghapus Pesanan ini?");
                    dialog.setCancelable(true);

                    idPemesanan = tvIdPemesanan.getText().toString();
                    dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //moveToPembayaran();
                            delete();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                    return false;
                }
            });
        }
        private void delete(){
            apiInterface = apiClient.getClient().create(apiInterface.class);
            Call<Pembayaran> pembayaranCall = apiInterface.pembayaranDelete(idPemesanan);
            pembayaranCall.enqueue(new Callback<Pembayaran>() {
                @Override
                public void onResponse(Call<Pembayaran> call, Response<Pembayaran> response) {
                    Toast.makeText(context, "Berhasil Terhapus, Silahkan Klik Tombol Reload", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Pembayaran> call, Throwable t) {
                    Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}