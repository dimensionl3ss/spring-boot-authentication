package com.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entity.UserInfo;
import com.auth.service.UserInfoService;
import com.auth.utilities.AuthRequest;
import com.auth.utilities.AuthResponse;
import com.auth.utilities.JwtService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired private UserInfoService userInfoService; 
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtService jwtService;
	@PostMapping("/signup")
	ResponseEntity<AuthResponse> signup(@RequestBody UserInfo userInfo) {
		userInfoService.addAUser(userInfo);
		final String token = jwtService.generateToken(userInfo.getUsername());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setStatus(true);
        authResponse.setToken(token);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	final String token = jwtService.generateToken(authRequest.getUsername());
            AuthResponse authResponse = new AuthResponse();
            authResponse.setStatus(true);
            authResponse.setToken(token);
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
	}
	
}
