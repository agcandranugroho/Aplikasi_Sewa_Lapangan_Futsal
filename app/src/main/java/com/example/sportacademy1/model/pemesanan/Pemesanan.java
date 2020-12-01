package com.example.sportacademy1.model.pemesanan;

import com.google.gson.annotations.SerializedName;

public class Pemesanan{

	@SerializedName("data")
	private PemesananData pemesananData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setPemesananData(PemesananData pemesananData){
		this.pemesananData = pemesananData;
	}

	public PemesananData getPemesananData(){
		return pemesananData;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}