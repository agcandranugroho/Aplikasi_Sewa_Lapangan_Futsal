package com.example.sportacademy1.model.pemesanan;

import com.google.gson.annotations.SerializedName;

public class PemesananData {

	@SerializedName("tanggal_main")
	private String tanggalMain;

	@SerializedName("durasi_start")
	private String durasiStart;

	@SerializedName("harga")
	private String harga;

	@SerializedName("id_lapangan")
	private String idLapangan;

	@SerializedName("durasi_end")
	private String durasiEnd;

	@SerializedName("id_pelanggan")
	private String idPelanggan;

	@SerializedName("id_pemesanan")
	private String idPemesanan;

	//tabel pembayaran
	@SerializedName("status")
	private String statusPembayaran;

	//tabel lapangan
	@SerializedName("nama_lapangan")
	private String namaLapangan;

	public void setIdPemesanan(String idPemesanan) {
		this.idPemesanan = idPemesanan;
	}

	public String getIdPemesanan(){
		return idPemesanan;
	}

	public void setTanggalMain(String tanggalMain){
		this.tanggalMain = tanggalMain;
	}

	public String getTanggalMain(){
		return tanggalMain;
	}

	public void setDurasiStart(String durasiStart){
		this.durasiStart = durasiStart;
	}

	public String getDurasiStart(){
		return durasiStart;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setIdLapangan(String idLapangan){
		this.idLapangan = idLapangan;
	}

	public String getIdLapangan(){
		return idLapangan;
	}

	public void setDurasiEnd(String durasiEnd){
		this.durasiEnd = durasiEnd;
	}

	public String getDurasiEnd(){
		return durasiEnd;
	}

	public void setIdPelanggan(String idPelanggan){
		this.idPelanggan = idPelanggan;
	}

	public String getIdPelanggan(){
		return idPelanggan;
	}

	//tabel pembayaran
	public void setStatusPembayaran(String statusPembayaran) {
		this.statusPembayaran = statusPembayaran;
	}

	public String getStatusPembayaran(){
		return statusPembayaran;
	}
	//tabel pembayaran

	//tabel lapangan
	public void setNamaLapangan(String namaLapangan){
		this.namaLapangan = namaLapangan;
	}
	public String getNamaLapangan(){
		return namaLapangan;
	}
	//tabel lapangan
}