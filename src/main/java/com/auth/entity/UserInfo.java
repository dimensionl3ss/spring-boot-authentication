package com.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserInfo {
	
	@Id
	String username;
	String password;
	String role;
}
