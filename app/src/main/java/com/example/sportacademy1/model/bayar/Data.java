package com.example.sportacademy1.model.bayar;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("id_pemesanan")
	private String idPemesanan;

	@SerializedName("jam_mulai")
	private String jamMulai;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_pembayaran")
	private String idPembayaran;

	@SerializedName("jam_selesai")
	private String jamSelesai;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("total_bayar")
	private String totalBayar;

	@SerializedName("nama_lapangan")
	private String namaLapangan;

	public void setIdPemesanan(String idPemesanan){
		this.idPemesanan = idPemesanan;
	}

	public String getIdPemesanan(){
		return idPemesanan;
	}

	public void setJamMulai(String jamMulai){
		this.jamMulai = jamMulai;
	}

	public String getJamMulai(){
		return jamMulai;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIdPembayaran(String idPembayaran){
		this.idPembayaran = idPembayaran;
	}

	public String getIdPembayaran(){
		return idPembayaran;
	}

	public void setJamSelesai(String jamSelesai){
		this.jamSelesai = jamSelesai;
	}

	public String getJamSelesai(){
		return jamSelesai;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setTotalBayar(String totalBayar){
		this.totalBayar = totalBayar;
	}

	public String getTotalBayar(){
		return totalBayar;
	}

	public void setNamaLapangan(String namaLapangan){
		this.namaLapangan = namaLapangan;
	}

	public String getNamaLapangan(){
		return namaLapangan;
	}
}