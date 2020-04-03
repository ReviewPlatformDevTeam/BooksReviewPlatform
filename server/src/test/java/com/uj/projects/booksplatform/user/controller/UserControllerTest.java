package com.uj.projects.booksplatform.user.controller;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.user.dto.LoginRequest;
import com.uj.projects.booksplatform.user.dto.LoginResponse;
import com.uj.projects.booksplatform.user.dto.LoginResult;
import com.uj.projects.booksplatform.user.service.LoginService;
import com.uj.projects.booksplatform.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private LoginService loginService;
    private UserController userController;
    private UserService userService;

    @BeforeEach
    void SetUp(){
        loginService = mock(LoginService.class);
        userService = mock(UserService.class);
        userController = new UserController(loginService, userService);
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

    @Test()
    void shouldThrowExceptionWhenUserNameIsEmpty(){
        // Arrange
        String password = Any.string();
        LoginRequest request = new LoginRequest("", password);

        // Act && Assert
        assertThrows(IllegalArgumentException.class, () -> { userController.Login(request);});
    }

    @Test()
    void shouldThrowExceptionWhenPasswordIsEmpty(){
        // Arrange
        String username = Any.string();
        LoginRequest request = new LoginRequest(username, "");

        // Act && Assert
        assertThrows(IllegalArgumentException.class, () -> { userController.Login(request);});
    }
}