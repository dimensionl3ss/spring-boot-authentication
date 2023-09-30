package com.auth.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.auth.utilities.JwtService;


@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired private UserInfoService userInfoService; 
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtService jwtService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@PostMapping("/signup")
	ResponseEntity<String> addAUser(@RequestBody UserInfo userInfo) {
		logger.info("register in process");
		return new ResponseEntity<String>(userInfoService.addAUser(userInfo), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(@RequestBody UserInfo userInfo) {
		logger.info("Logging in process");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken(userInfo.getUsername()));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
	}
	
}
