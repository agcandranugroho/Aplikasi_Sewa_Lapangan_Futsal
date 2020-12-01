package com.example.sportacademy1.model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

	@SerializedName("nama")
	private String nama;

	@SerializedName("nomor_telepon")
	private String nomorTelepon;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNomorTelepon(String nomorTelepon){
		this.nomorTelepon = nomorTelepon;
	}

	public String getNomorTelepon(){
		return nomorTelepon;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}