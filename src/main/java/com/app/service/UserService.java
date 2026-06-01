package com.app.service;

import com.app.dto.LoginRequest;
import com.app.dto.RegisterRequest;
import com.app.entity.User;

public interface UserService {

	User register(RegisterRequest request);
	
	String login(LoginRequest request);
}
