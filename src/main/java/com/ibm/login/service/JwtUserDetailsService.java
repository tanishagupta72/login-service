package com.ibm.login.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.login.entity.UserDAO;
import com.ibm.login.exception.UserAlreadyExistsException;
import com.ibm.login.exception.UserNotFoundException;
import com.ibm.login.repository.LoginServiceRepository;
import com.ibm.login.util.JwtUtil;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	LoginServiceRepository loginRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		UserDAO user = loginRepo.findByUserName(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("User not found with username : "+username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
		
	}

	
	public UserDAO saveUser(UserDAO user)
	{
		UserDAO existingUser = loginRepo.findByUserName(user.getUserName());
		if(existingUser == null)
		{

		UserDAO newUser = new UserDAO();
		newUser.setUserName(user.getUserName());
		newUser.setPassword(encoder.encode(user.getPassword()));
		newUser.setCretTs(new Date());
		newUser.setUpdtTs(new Date());
		return loginRepo.save(newUser);
		}
		else
		{
			throw new UserAlreadyExistsException();
		}
		
	}
	
	public boolean isValidToken(String token,String userName) {
		
		return jwtUtil.validateToken(token, loadUserByUsername(userName));
	}
}
