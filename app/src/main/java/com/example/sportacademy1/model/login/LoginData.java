package com.example.sportacademy1.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("id_user")
	private String userId;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
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