package com.example.sportacademy1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportacademy1.API.apiInterface;
import com.example.sportacademy1.R;
import com.example.sportacademy1.model.lapangan.LapanganData;

import java.util.ArrayList;
import java.util.List;

public class AdapterDataJadwalMain extends RecyclerView.Adapter<AdapterDataJadwalMain.HolderData> {
    private Context context;
    apiInterface apiInterface;

    private List<LapanganData> lapanganDataList;
    private List<LapanganData> cariList;

    public AdapterDataJadwalMain(Context context, List<LapanganData> lapanganDataList) {
        this.context = context;
        this.lapanganDataList = lapanganDataList;
        this.cariList=lapanganDataList;
    }

    @NonNull
    @Override
    public AdapterDataJadwalMain.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_jadwal_main,parent,false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataJadwalMain.HolderData holder, int position) {
        LapanganData lapanganData = lapanganDataList.get(position);
        holder.TVtanggal.setText(lapanganData.getTanggalMain());
        holder.TVlapangan.setText(lapanganData.getNamaLapangan());
        holder.TVjamMulai.setText(lapanganData.getJamMulai()+":"+"00");
        holder.TVjamSelesai.setText(lapanganData.getJamSelesai()+":"+"00");
    }

    @Override
    public int getItemCount() {
        return lapanganDataList.size();
    }


    public class HolderData extends RecyclerView.ViewHolder {
        TextView TVtanggal,TVlapangan,TVjamMulai,TVjamSelesai;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            TVtanggal = itemView.findViewById(R.id.CI_tanggal);
            TVlapangan = itemView.findViewById(R.id.CI_lapangan);
            TVjamMulai = itemView.findViewById(R.id.CI_jamMulai);
            TVjamSelesai= itemView.findViewById(R.id.CI_jamSelesai);
        }
    }
}

