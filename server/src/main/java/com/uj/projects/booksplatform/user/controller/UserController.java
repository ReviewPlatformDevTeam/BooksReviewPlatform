package com.uj.projects.booksplatform.user.controller;


import com.uj.projects.booksplatform.user.dto.LoginRequest;
import com.uj.projects.booksplatform.user.dto.LoginResponse;
import com.uj.projects.booksplatform.user.dto.LoginResult;


import com.uj.projects.booksplatform.user.entity.*;
import com.uj.projects.booksplatform.user.service.LoginService;
import com.uj.projects.booksplatform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    private final LoginService loginService;
    private final UserService userService;

    @Autowired
    public UserController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse Login(LoginRequest loginRequest){
        LoginResult result = loginService.Login(loginRequest.getUsername());
       return new LoginResponse(result.isSuccess(), result.getToken());
    }

    @PostMapping("/users")
    public User registerUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }
    
    @PostMapping("/resetPassword")
    public PasswordResetResponse ResetPassword(@RequestBody PasswordResetRequest request){
        return new PasswordResetResponse(true, "");
    }

}
