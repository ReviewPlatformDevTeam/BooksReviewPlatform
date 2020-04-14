package com.uj.projects.booksplatform.user.controller;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.user.entity.*;
import com.uj.projects.booksplatform.user.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private LoginService loginService;
    private UserController userController;

    @BeforeEach
    void SetUp(){
        loginService = mock(LoginService.class);
        userController = new UserController(loginService);
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

    @Test()
    void shouldReturnSuccessDuringPassWordReset(){
        // Arrange
        PasswordResetRequest request = Any.instanceOf(PasswordResetRequest.class);

        // Act
        PasswordResetResponse response = userController.ResetPassword(request);

        // Assert
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(response.getErrorMessage(), "");
    }
}