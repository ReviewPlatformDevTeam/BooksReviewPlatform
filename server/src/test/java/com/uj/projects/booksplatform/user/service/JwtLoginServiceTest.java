package com.uj.projects.booksplatform.user.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.user.entity.LoginResult;
import com.uj.projects.booksplatform.user.resources.LoginResources;
import com.uj.projects.booksplatform.user.wrappers.JwtBuilderWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.mockito.Mockito.*;

class JwtLoginServiceTest {

    private JwtBuilderWrapper jwtBuilderWrapper;

    private LoginService loginService;
    private static final long tokeLifetime = 3600;
    private byte[] jwtSecret = LoginResources.JWT_SECRET;

    @BeforeEach
    void setUp(){
        jwtBuilderWrapper = mock(JwtBuilderWrapper.class);
        loginService = new JwtLoginService(jwtBuilderWrapper);
    }

    @Test
    void shouldCallJwlBuilderWrapperAndReturnSuccessResult() {
        // Arrange
        String username = Any.string();
        String role = "user";
        String token = Any.string();
        when(jwtBuilderWrapper.generateToken(username, tokeLifetime, role, jwtSecret)).thenReturn(token);
        
        // Act
        LoginResult actual = loginService.Login(username);
        
        // Assert
        verify(jwtBuilderWrapper, atLeastOnce()).generateToken(username, tokeLifetime, role, jwtSecret);
        Assert.assertTrue(actual.isSuccess());
        Assert.assertEquals(actual.getToken(), token);
    }
}