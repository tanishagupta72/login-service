package com.ibm.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.login.repository.LoginServiceRepository;

@Service
public class LoginService {

	@Autowired
	LoginServiceRepository loginRepo;
	
	
}
