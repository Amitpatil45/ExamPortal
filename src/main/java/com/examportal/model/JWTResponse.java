package com.examportal.model;

public class JWTResponse {

	String token;

	String userName;
	String password;
	String fName;
	String lname;
	String email;
	String phone;
	public JWTResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JWTResponse(String token, String userName, String password, String fName, String lname, String email,
			String phone) {
		super();
		this.token = token;
		this.userName = userName;
		this.password = password;
		this.fName = fName;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


}
