package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.entity.UserInfo;
import com.auth.repository.UserInfoRepository;
import com.auth.utilities.AuthRequest;
import com.auth.utilities.AuthResponse;
import com.auth.utilities.JwtService;

@Service
public class AuthService {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public AuthResponse signupUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		final String token = jwtService.generateToken(userInfo.getUsername());
		AuthResponse authResponse = new AuthResponse();
		authResponse.setStatus(true);
		authResponse.setToken(token);
		return authResponse;
	}

	public AuthResponse loginUser(AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			final String token = jwtService.generateToken(authRequest.getUsername());
			AuthResponse authResponse = new AuthResponse();
			authResponse.setStatus(true);
			authResponse.setToken(token);
			return authResponse;
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}
}
