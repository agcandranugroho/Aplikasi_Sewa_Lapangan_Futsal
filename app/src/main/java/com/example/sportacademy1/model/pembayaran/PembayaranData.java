package com.example.sportacademy1.model.pembayaran;

import com.google.gson.annotations.SerializedName;

public class PembayaranData {

	@SerializedName("id_pembayaran")
	private String idPembayaran;

	@SerializedName("total_bayar")
	private String totalBayar;

	@SerializedName("id_pemesanan")
	private String idPemesanan;

	@SerializedName("status")
	private String status;

	// GET NAMA LAPANGAN
	@SerializedName("nama_lapangan")
	private String namaLapangan;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("jam_mulai")
	private String jamMulai;

	@SerializedName("jam_selesai")
	private String jamSelesai;

	//GET USER
	@SerializedName("nama")
	private String nama;

	public void setIdPembayaran(String idPembayaran) {
		this.idPembayaran = idPembayaran;
	}

	public String getIdPembayaran() {
		return idPembayaran;
	}

	public void setTotalBayar(String totalBayar){
		this.totalBayar = totalBayar;
	}

	public String getTotalBayar(){
		return totalBayar;
	}

	public void setIdPemesanan(String idPemesanan){
		this.idPemesanan = idPemesanan;
	}

	public String getIdPemesanan(){
		return idPemesanan;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setNamaLapangan(String namaLapangan){
		this.namaLapangan = namaLapangan;
	}

	public String getNamaLapangan() {
		return namaLapangan;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setJamMulai(String jamMulai){
		this.jamMulai = jamMulai;
	}

	public String getJamMulai(){
		return jamMulai;
	}

	public void setJamSelesai(String jamSelesai){
		this.jamSelesai = jamSelesai;
	}

	public String getJamSelesai(){
		return jamSelesai;
	}

	public void setNama(String nama){
		this.nama = nama;
	}
	public String getNama(){
		return nama;
	}
}