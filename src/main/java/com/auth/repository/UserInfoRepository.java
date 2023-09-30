package com.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}
