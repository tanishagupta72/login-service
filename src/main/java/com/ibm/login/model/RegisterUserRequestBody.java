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
public class RegisterUserRequestBody {

	@JsonProperty("UserName")
	private String userName;
	
	@JsonProperty("Password")
	private String password;
}
