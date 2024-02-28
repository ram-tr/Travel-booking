package com.example.demo.entity;

import jakarta.validation.constraints.Email;

public class LoginCredentials {
	
	
	@Email
	private String username;
	
	private String password;
	
	public LoginCredentials()
	{
		
	}
	
	public LoginCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
