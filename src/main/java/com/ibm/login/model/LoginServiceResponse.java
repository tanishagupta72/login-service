package com.ibm.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginServiceResponse {

	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("UserToken")
	private String userToken;
	
	@JsonProperty("ServiceToken")
	private String serviceToken;
	
	@JsonProperty("TransactionToken")
	private String transactionToken;
	
	public LoginServiceResponse(String userToken)
	{
		this.userToken=userToken;
	}
}
