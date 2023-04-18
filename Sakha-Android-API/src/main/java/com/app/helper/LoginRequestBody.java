package com.app.helper;

public class LoginRequestBody {
	private String userEmail;
	private String password;
	public String getUserEmail() {
		return userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginRequestBody [userEmail=" + userEmail + ", password=" + password + "]";
	}
	
}
