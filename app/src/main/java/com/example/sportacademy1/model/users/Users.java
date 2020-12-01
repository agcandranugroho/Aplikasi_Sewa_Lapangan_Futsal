package com.example.sportacademy1.model.users;

import com.google.gson.annotations.SerializedName;

public class Users{

	@SerializedName("data")
	private UsersData usersData;

	@SerializedName("status")
	private boolean status;

	@SerializedName("message")
	private String message;

	public void setUsersData(UsersData usersData){
		this.usersData = usersData;
	}

	public UsersData getUsersData(){
		return usersData;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}
}