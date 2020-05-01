package com.uj.projects.booksplatform.user.controller;

import autofixture.publicinterface.Any;

import com.uj.projects.booksplatform.user.dto.LoginRequest;
import com.uj.projects.booksplatform.user.dto.LoginResponse;
import com.uj.projects.booksplatform.user.dto.LoginResult;
import com.uj.projects.booksplatform.user.entity.User;

import com.uj.projects.booksplatform.user.entity.*;

import com.uj.projects.booksplatform.user.service.LoginService;
import com.uj.projects.booksplatform.user.service.PasswordResetService;
import com.uj.projects.booksplatform.user.service.UserNotFoundException;
import com.uj.projects.booksplatform.user.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private LoginService loginService;
    private UserController userController;
    private UserService userService;
    private PasswordResetService passwordResetService;


    @BeforeEach
    void SetUp(){
        loginService = mock(LoginService.class);
        userService = mock(UserService.class);
        passwordResetService = mock(PasswordResetService.class);
        userController = new UserController(loginService, userService, passwordResetService);
    }

    @Test
    void shouldCallRepoAndReturnUserWhenProperArgumentsProvided(){
        // Arrange
        User requestBody = new User();
        requestBody.setPassword(Any.string());
        requestBody.setEmail(Any.string());
        requestBody.setUsername(Any.string());
        given(userService.createUser(requestBody)).willReturn(requestBody);

        // Act
        User newUser = userController.registerUser(requestBody);

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
        when(loginService.Login(username)).thenReturn(loginResult);

        // Act
        LoginResponse actual = userController.Login(request);

        // Assert
        verify(loginService, atLeastOnce()).Login(username);
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
        Assert.assertTrue(response.isSuccess());
        verify(passwordResetService, times(1)).resetPassword(email);
        Assert.assertEquals(response.getErrorMessage(), "");
    }

    @SneakyThrows
    @Test
    void shouldReturnErrorWhenPasswordResetServiceThrowsException(){
        // Arrange
        PasswordResetRequest request = new PasswordResetRequest();
        String email = Any.string();
        request.setEmail(email);
        Mockito.doThrow(new UserNotFoundException()).when(passwordResetService).resetPassword(email);

        // Act
        PasswordResetResponse actual = userController.ResetPassword(request);

        // Assert
        Assert.assertFalse(actual.isSuccess());
        Assert.assertEquals(actual.getErrorMessage(), new UserNotFoundException().getMessage());
    }

}