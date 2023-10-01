package com.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.entity.UserInfo;
import com.auth.entity.UserInfoDetails;
import com.auth.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo userInfo = userInfoRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		return new UserInfoDetails(userInfo);
	}

	public List<UserInfo> getAllUsers() {
		return userInfoRepository.findAll();
	}
}
