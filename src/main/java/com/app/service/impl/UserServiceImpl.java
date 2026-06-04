package com.app.service.impl;


import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.dto.RegisterRequest;
import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import java.util.Optional;
import com.app.dto.LoginRequest;
import com.app.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, 
    		               PasswordEncoder passwordEncoder) {
    	
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

	@Override
	public User register(RegisterRequest request) {
		
		  User user = new User();

	        user.setName(request.getName());
	        user.setEmail(request.getEmail());
	        user.setPassword(
	        		passwordEncoder.encode(request.getPassword())
	        		);

	        user.setRole("USER");

	        return userRepository.save(user);
	    }

	@Override
	public String login(LoginRequest request) {
		
		System.out.println("Email entered: " + request.getEmail());
		System.out.println("All users: " + userRepository.findAll());
		
		Optional<User> optionalUser =
				userRepository.findByEmail(request.getEmail());
		
		if(optionalUser.isEmpty()) {
			
			return "Email not found ";
			
		}
		
		User user = optionalUser.get();
		
		boolean isPasswordMatch=
				passwordEncoder.matches(request.getPassword(), user.getPassword()
						);
		
		if(!isPasswordMatch) {
			return "Invalid Password";
			
		}
		String token = JwtUtil.generateToken(user.getEmail());

        return token;
				
				
	}
		
		
		
	}































