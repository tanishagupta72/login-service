package com.ibm.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




public class LoginServiceRequest {

	@JsonProperty("UserName")
	private String userName;
	
	@JsonProperty("Password")
	private String password;
	
	public LoginServiceRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public LoginServiceRequest()
	{
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
	
	}

