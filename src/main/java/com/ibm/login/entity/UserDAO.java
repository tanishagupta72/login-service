package com.ibm.login.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name = "USER" , uniqueConstraints=@UniqueConstraint(columnNames = {"USER_NAME"}))
public class UserDAO {
	
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private int userId;
	
	@Column	(name ="USER_NAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "CRET_TS")
	private Date cretTs;
	
	@Column(name = "UPST_TS")
	private Date updtTs;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public Date getCretTs() {
		return cretTs;
	}

	public void setCretTs(Date cretTs) {
		this.cretTs = cretTs;
	}

	public Date getUpdtTs() {
		return updtTs;
	}

	public void setUpdtTs(Date updtTs) {
		this.updtTs = updtTs;
	}

	public UserDAO(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}

}
