package com.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String>{
	
}
