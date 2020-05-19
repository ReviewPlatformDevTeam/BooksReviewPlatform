package com.uj.projects.booksplatform.user.controller;

import autofixture.publicinterface.Any;

import com.uj.projects.booksplatform.user.dto.LoginRequest;
import com.uj.projects.booksplatform.user.dto.LoginResponse;
import com.uj.projects.booksplatform.user.dto.LoginResult;
import com.uj.projects.booksplatform.user.dto.UserDto;
import com.uj.projects.booksplatform.user.entity.User;

import com.uj.projects.booksplatform.user.entity.*;

import com.uj.projects.booksplatform.user.mapper.UserMapper;
import com.uj.projects.booksplatform.user.service.LoginService;
import com.uj.projects.booksplatform.user.service.PasswordResetService;
import com.uj.projects.booksplatform.user.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private LoginService loginService;
    private UserController userController;
    private UserService userService;
    private PasswordResetService passwordResetService;
    private UserMapper userMapper;

    @BeforeEach
    void SetUp(){
        loginService = mock(LoginService.class);
        userService = mock(UserService.class);
        passwordResetService = mock(PasswordResetService.class);
        userMapper = mock(UserMapper.class);
        userController = new UserController(loginService, userService, passwordResetService, userMapper);
    }

    @Test
    void shouldCallRepoAndReturnUserWhenProperArgumentsProvided(){
        // Arrange
        User user = new User();
        user.setPassword(Any.string());
        user.setEmail(Any.string());
        user.setUsername(Any.string());

        UserDto requestBody = mapUserToUserDto(user);
        when(userMapper.userDtoToUser(requestBody)).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(requestBody);
        given(userService.createUser(user)).willReturn(user);

        // Act
        UserDto newUser = userController.registerUser(requestBody);

        // Assert
        Assert.assertEquals(newUser.getPassword(), requestBody.getPassword());
        Assert.assertEquals(newUser.getUsername(), requestBody.getUsername());
        Assert.assertEquals(newUser.getEmail(), requestBody.getEmail());
    }

    @Test
    void shouldCallLoginServiceAndReturnResultWhenProperUserNameAddPasswordAreProvided() {
        // Arrange
        String username = Any.string();
        String password = Any.string();
        LoginRequest request = new LoginRequest(username, password);
        boolean successLogin = true;
        String token = Any.string();
        LoginResult loginResult = new LoginResult(successLogin, token);
        when(loginService.Login(username, password)).thenReturn(loginResult);

        // Act
        LoginResponse actual = userController.Login(request);

        // Assert
        verify(loginService, atLeastOnce()).Login(username, password);
        Assert.assertTrue(actual.isSuccess());
        Assert.assertEquals(actual.getToken(), token);
    }

    @SneakyThrows
    @Test()
    void shouldCallPasswordResetServiceAndReturnResponse() {
        // Arrange
        PasswordResetRequest request = new PasswordResetRequest();
        String email = Any.string();
        request.setEmail(email);

        // Act
        PasswordResetResponse response = userController.ResetPassword(request);

        // Asserts
        verify(passwordResetService, times(1)).resetPassword(email);
        Assert.assertNotNull(response);
    }

    UserDto mapUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    User mapUserDtoToUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}