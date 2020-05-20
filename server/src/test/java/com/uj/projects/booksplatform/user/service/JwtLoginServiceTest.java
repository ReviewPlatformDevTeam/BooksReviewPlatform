package com.uj.projects.booksplatform.user.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.error.exception.BadCredentialsException;
import com.uj.projects.booksplatform.user.dto.LoginResult;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.resources.LoginResources;
import com.uj.projects.booksplatform.user.wrappers.JwtBuilderWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.Assert;

import static org.mockito.Mockito.*;

class JwtLoginServiceTest {

    private JwtBuilderWrapper jwtBuilderWrapper;

    private LoginService loginService;
    private static final long tokeLifetime = 3600;
    private byte[] jwtSecret = LoginResources.JWT_SECRET;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){
        jwtBuilderWrapper = mock(JwtBuilderWrapper.class);
        userService = mock(UserServiceImpl.class);
        passwordEncoder = mock(PasswordEncoder.class);
        loginService = new JwtLoginService(jwtBuilderWrapper, userService, passwordEncoder);
    }

    @Test
    void shouldCallJwtBuilderWrapperAndReturnSuccessResult() {
        // Arrange
        String username = Any.string();
        String password = Any.string();
        String role = "user";
        String token = Any.string();
        User userFromDb = new User();
        userFromDb.setPassword(password);
        when(jwtBuilderWrapper.generateToken(username, tokeLifetime, role, jwtSecret)).thenReturn(token);
        when(userService.getUserByUsername(username)).thenReturn(userFromDb);
        when(passwordEncoder.matches(password, userFromDb.getPassword())).thenReturn(true);

        // Act
        LoginResult actual = loginService.Login(username, password);

        // Assert
        verify(jwtBuilderWrapper, atLeastOnce()).generateToken(username, tokeLifetime, role, jwtSecret);
        Assert.assertTrue(actual.isSuccess());
        Assert.assertEquals(actual.getToken(), token);
    }

    @Test
    void shouldCallJwtBuilderWrapperWithBadUsernameAndThrowException() {
        // Arrange
        String username = Any.string();
        String password = Any.string();
        String role = "user";
        String token = Any.string();
        when(jwtBuilderWrapper.generateToken(username, tokeLifetime, role, jwtSecret)).thenReturn(token);
        when(userService.getUserByUsername(username)).thenReturn(null);

        // Assert
        Assert.assertThrows(BadCredentialsException.class, () -> loginService.Login(username, password));
        verify(jwtBuilderWrapper, never()).generateToken(username, tokeLifetime, role, jwtSecret);
    }

    @Test
    void shouldCallJwtBuilderWrapperWithBadPasswordAndThrowException() {
        // Arrange
        String username = Any.string();
        String badPassword = Any.string();
        String validPassword = badPassword + "sufix";
        String role = "user";
        String token = Any.string();
        User userFromDb = new User();
        userFromDb.setPassword(validPassword);
        when(jwtBuilderWrapper.generateToken(username, tokeLifetime, role, jwtSecret)).thenReturn(token);
        when(userService.getUserByUsername(username)).thenReturn(userFromDb);

        // Assert
        Assert.assertThrows(BadCredentialsException.class, () -> loginService.Login(username, badPassword));
        verify(jwtBuilderWrapper, never()).generateToken(username, tokeLifetime, role, jwtSecret);
    }
}