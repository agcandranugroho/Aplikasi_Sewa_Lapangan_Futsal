package com.example.sportacademy1.model.lapangan;

import com.google.gson.annotations.SerializedName;

public class LapanganData {

	@SerializedName("tanggal_main")
	private String tanggalMain;

	@SerializedName("jam_mulai")
	private String jamMulai;

	@SerializedName("nama_lapangan")
	private String namaLapangan;

	@SerializedName("jam_selesai")
	private String jamSelesai;

	public void setTanggalMain(String tanggalMain){
		this.tanggalMain = tanggalMain;
	}

	public String getTanggalMain(){
		return tanggalMain;
	}

	public void setJamMulai(String jamMulai){
		this.jamMulai = jamMulai;
	}

	public String getJamMulai(){
		return jamMulai;
	}

	public void setNamaLapangan(String namaLapangan){
		this.namaLapangan = namaLapangan;
	}

	public String getNamaLapangan(){
		return namaLapangan;
	}

	public void setJamSelesai(String jamSelesai){
		this.jamSelesai = jamSelesai;
	}

	public String getJamSelesai(){
		return jamSelesai;
	}
}