package com.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entity.UserInfo;
import com.auth.service.UserInfoService;

@RestController
@RequestMapping("/api")
public class Controller {
	@Autowired
	private UserInfoService userInfoService;
	@GetMapping("/sayhello")
	@PreAuthorize("hasAuthority('user')")
	ResponseEntity<String> seyHello() {
		return ResponseEntity.ok("Hello World");
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasAuthority('admin')")
	ResponseEntity<List<UserInfo>> getUsers() {
		return new ResponseEntity<List<UserInfo>>(userInfoService.getAllUsers(), HttpStatus.OK);
	}
}
