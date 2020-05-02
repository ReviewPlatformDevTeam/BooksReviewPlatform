package com.uj.projects.booksplatform.user.service;

import autofixture.publicinterface.Any;
import com.uj.projects.booksplatform.user.service.email.EmailSender;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PasswordResetServiceTests {

    private PasswordResetService sut;
    private EmailSender emailSender;
    private UserService userService;

    @BeforeEach
    public void Setup(){
        emailSender = mock(EmailSender.class);
        userService = mock(UserService.class);
        sut = new PasswordResetServiceImpl(emailSender, userService);
    }

    @SneakyThrows
    @Test
    public void shouldCallEmailSender(){
        // Arrange
        String email = Any.string();
        String newPassword = Any.string();
        when(userService.resetPassword(email)).thenReturn(newPassword);

        // Act
        sut.resetPassword(email);

        // Assert
        verify(emailSender, times(1)).SendEmail("no-reply@booksreviewplatform.pl", email,"Password reset for Books Review Platform", "Your new pass: " + newPassword);
    }
}
