package com.example.sportacademy1.model.pembayaran;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Pembayaran{

	@SerializedName("data")
	private List<PembayaranData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<PembayaranData> data){
		this.data = data;
	}

	public List<PembayaranData> getData(){
		return data;
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