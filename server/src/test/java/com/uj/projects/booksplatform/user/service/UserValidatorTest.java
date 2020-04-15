package com.uj.projects.booksplatform.user.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import com.uj.projects.booksplatform.user.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserValidatorTest {

    private UserRepository userRepository;
    private UserValidator userValidator;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        userValidator = new UserValidator(userRepository);
    }

    @Test
    void shouldThrowExceptionWhenGivenUsernameAlreadyExists(){
        // Arrange
        String username = Any.string();
        String email = Any.string();
        User user = new User();
        given(userRepository.findUserByUsername(username)).willReturn(user);

        // Act && Assert
        assertThrows(DefaultRuntimeException.class, () -> { userValidator.validateIfUserAlreadyRegistered(username, email);});
    }

    @Test
    void shouldThrowExceptionWhenGivenEmailAlreadyExists(){
        // Arrange
        String username = Any.string();
        String email = Any.string();
        User user = new User();
        given(userRepository.findUserByEmail(email)).willReturn(user);

        // Act && Assert
        assertThrows(DefaultRuntimeException.class, () -> { userValidator.validateIfUserAlreadyRegistered(username, email);});
    }
}
