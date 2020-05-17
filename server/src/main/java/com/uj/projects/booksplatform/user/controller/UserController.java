package com.uj.projects.booksplatform.user.controller;

import com.uj.projects.booksplatform.user.dto.LoginRequest;
import com.uj.projects.booksplatform.user.dto.LoginResponse;
import com.uj.projects.booksplatform.user.dto.LoginResult;


import com.uj.projects.booksplatform.user.dto.UserDto;
import com.uj.projects.booksplatform.user.entity.*;
import com.uj.projects.booksplatform.user.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Autowired
    public UserController(LoginService loginService, UserService userService, PasswordResetService passwordResetService, UserMapper userMapper) {
        this.loginService = loginService;
        this.userService = userService;
        this.passwordResetService = passwordResetService;
        this.userMapper = userMapper;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public LoginResponse Login(@Valid@RequestBody LoginRequest loginRequest) {
        LoginResult result = loginService.Login(loginRequest.getUsername(), loginRequest.getPassword());
       return new LoginResponse(result.isSuccess(), result.getToken());
    }
    @PostMapping("/users")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto){
        User user = userMapper.userDtoToUser(userDto);
        System.out.println(user.getPassword());
        User createdUser = userService.createUser(user);
        return userMapper.userToUserDto(createdUser);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/resetPassword")
    public PasswordResetResponse ResetPassword(@RequestBody PasswordResetRequest request){
        boolean result = passwordResetService.resetPassword(request.getEmail());
        return new PasswordResetResponse(result);
    }
}
