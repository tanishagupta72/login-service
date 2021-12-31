package com.ibm.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.login.entity.UserDAO;
import com.ibm.login.model.LoginServiceRequest;
import com.ibm.login.model.LoginServiceResponse;
import com.ibm.login.model.RegisterUserRequestBody;
import com.ibm.login.model.RegisterUserResponseBody;
import com.ibm.login.model.ValidateTokenRequestBody;
import com.ibm.login.model.ValidateTokenResponseBody;
import com.ibm.login.repository.LoginServiceRepository;
import com.ibm.login.service.JwtUserDetailsService;
import com.ibm.login.util.JwtUtil;

@RestController
@CrossOrigin
public class LoginServiceController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private LoginServiceRepository repository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequestBody request) throws Exception
	{
		UserDAO user = new UserDAO(request.getUserName(),request.getPassword());
		UserDAO savedUser = jwtUserDetailsService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.OK).body( new RegisterUserResponseBody("SUCCESS",savedUser.getUserId()));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginServiceRequest request) throws Exception
	{
		authenticate(request.getUserName(),request.getPassword());
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUserName());
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.status(HttpStatus.OK).body(new LoginServiceResponse(token));
	}
	
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateToken(@RequestBody ValidateTokenRequestBody request) throws Exception
	{
		int userId;
		boolean isValid = jwtUserDetailsService.isValidToken(request.getToken(), request.getUserName());
		if(isValid){
		    userId = repository.findByUserName(request.getUserName()).getUserId();
			}
		else
			userId = 0 ;
		return ResponseEntity.status(HttpStatus.OK).body(new ValidateTokenResponseBody(isValid,userId));
	}
	
	
	
	/*
	 * @PostMapping("/login") public ResponseEntity<?> login(@RequestBody
	 * LoginServiceRequest request) { return
	 * ResponseEntity.status(HttpStatus.OK).body("Hi"+request.getUserName()); }
	 */
	private void authenticate(String username, String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e)
		{
			throw new Exception("USER_DISABLED",e);
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
}
