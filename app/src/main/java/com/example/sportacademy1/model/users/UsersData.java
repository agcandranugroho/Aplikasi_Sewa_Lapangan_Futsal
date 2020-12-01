package com.example.sportacademy1.model.users;

import com.google.gson.annotations.SerializedName;

public class UsersData {

	@SerializedName("password")
	private String password;

	//@SerializedName("role")
	//private String role;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_user")
	private String id;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	//public void setRole(String role){this.role = role;	}

	//public String getRole(){		return role;	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
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