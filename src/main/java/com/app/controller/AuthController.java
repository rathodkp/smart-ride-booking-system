package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.RegisterRequest;
import com.app.entity.User;
import com.app.service.UserService;
import com.app.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                userService.register(request)
        );
        
    }
        
        
        @PostMapping("/login")
        public ResponseEntity<String> login(
                @RequestBody LoginRequest request) {

            return ResponseEntity.ok(
                    userService.login(request)
            );
        }
        @RestController
        public class TestController {

            @GetMapping("/test")
            public String test() {
                return "API Working";
            }
        }
    }
















