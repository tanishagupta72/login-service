package com.ibm.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.login.entity.UserDAO;

public interface LoginServiceRepository extends JpaRepository<UserDAO, Integer> {

	public UserDAO findByUserName(String username);

}
