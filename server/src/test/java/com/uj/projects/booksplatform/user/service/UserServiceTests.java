package com.uj.projects.booksplatform.user.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import com.uj.projects.booksplatform.user.validator.UserValidator;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

public class UserServiceTests {

    private UserService sut;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserValidator userValidator;

    @BeforeEach
    public void SetUp(){
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userValidator = mock(UserValidator.class);
        sut = new UserServiceImpl(userRepository, passwordEncoder, userValidator);
    }

    @SneakyThrows
    @Test
    public void shouldGenerateNewPasswordAndUpdateIt(){
        // Arrange
        String email = Any.string();
        String expectedPassword = "12345678910";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findUserByEmail(email)).thenReturn(user);

        // Act
        String actualNewPassword = sut.resetPassword(email);

        // Assert
        verify(userRepository, times(1)).save(user);
        Assert.assertEquals(expectedPassword, actualNewPassword);
    }
    @Test
    public void shouldUpdateUser(){
        // Arrange
        String password = Any.string();
        User user = new User();
        String encodedPassword = Any.stringOtherThan(password);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // Act
        sut.updateUser(user);

        // Assert
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldDeleteUser(){
        // Arrange
        int userId = Any.intValue();

        // Act
        sut.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}
