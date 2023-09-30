package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entity.UserInfo;
import com.auth.service.AuthService;
import com.auth.utilities.AuthRequest;
import com.auth.utilities.AuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	ResponseEntity<AuthResponse> signup(@RequestBody UserInfo userInfo) {
		return new ResponseEntity<AuthResponse>(authService.signupUser(userInfo), HttpStatus.OK);
	}

	@PostMapping("/login")
	ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		return new ResponseEntity<AuthResponse>(authService.loginUser(authRequest), HttpStatus.OK);
	}
}
