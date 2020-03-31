package com.uj.projects.booksplatform.user.controller;

import com.uj.projects.booksplatform.user.entity.LoginRequest;
import com.uj.projects.booksplatform.user.entity.LoginResponse;
import com.uj.projects.booksplatform.user.entity.LoginResult;
import com.uj.projects.booksplatform.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

    private LoginService loginService;

    @Autowired
    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponse Login(@RequestBody LoginRequest loginRequest){

        if (loginRequest.getUsername().isEmpty()){
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (loginRequest.getPassword().isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty");
        }
        LoginResult result = loginService.Login(loginRequest.getUsername());

       return new LoginResponse(result.isSuccess(), result.getToken());
    }
}
