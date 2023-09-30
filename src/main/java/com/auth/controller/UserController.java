package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entity.UserInfo;
import com.auth.service.UserInfoService;

@RestController
public class UserController {
	@Autowired private UserInfoService userInfoService; 
	@PostMapping("/newuser")
	ResponseEntity<String> addAUser(UserInfo userInfo) {
		
		return new ResponseEntity<String>(userInfoService.addAUser(userInfo), HttpStatus.CREATED);
	}
}
