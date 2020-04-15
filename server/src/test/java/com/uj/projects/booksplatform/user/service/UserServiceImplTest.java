package com.uj.projects.booksplatform.user.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void shouldThrowExceptionWhenGivenUsernameAlreadyExists(){
        // Arrange
        String username = Any.string();
        String email = Any.string();
        User user = new User();
        given(userRepository.findUserByUsername(username)).willReturn(user);

        // Act && Assert
        assertThrows(DefaultRuntimeException.class, () -> { userService.validateIfUserAlreadyRegistered(username, email);});
    }

    @Test
    void shouldThrowExceptionWhenGivenEmailAlreadyExists(){
        // Arrange
        String username = Any.string();
        String email = Any.string();
        User user = new User();
        given(userRepository.findUserByEmail(email)).willReturn(user);

        // Act && Assert
        assertThrows(DefaultRuntimeException.class, () -> { userService.validateIfUserAlreadyRegistered(username, email);});
    }
}
