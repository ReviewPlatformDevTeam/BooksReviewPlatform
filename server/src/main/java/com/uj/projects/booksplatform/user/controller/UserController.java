package com.uj.projects.booksplatform.user.controller;


import com.uj.projects.booksplatform.user.dto.LoginRequest;
import com.uj.projects.booksplatform.user.dto.LoginResponse;
import com.uj.projects.booksplatform.user.dto.LoginResult;


import com.uj.projects.booksplatform.user.entity.*;
import com.uj.projects.booksplatform.user.service.LoginService;
import com.uj.projects.booksplatform.user.service.PasswordResetService;
import com.uj.projects.booksplatform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    private final LoginService loginService;
    private final UserService userService;
    private final PasswordResetService passwordResetService;

    @Autowired
    public UserController(LoginService loginService, UserService userService, PasswordResetService passwordResetService) {
        this.loginService = loginService;
        this.userService = userService;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/login")
    public LoginResponse Login(@Valid@RequestBody LoginRequest loginRequest){
        LoginResult result = loginService.Login(loginRequest.getUsername());
       return new LoginResponse(result.isSuccess(), result.getToken());
    }


    @PostMapping("/users")
    public User registerUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }


    @PostMapping("/resetPassword")
    public PasswordResetResponse ResetPassword(@Valid@RequestBody PasswordResetRequest request){
        boolean passwordResetResult = passwordResetService.resetPassword(request.getEmail());
        return new PasswordResetResponse(passwordResetResult);
    }

}
