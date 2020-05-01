package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.service.email.EmailSender;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class PasswordResetServiceTests {

    private PasswordResetService passwordResetService;
    private EmailSender emailSender;
    private UserService userService;

    @BeforeEach
    public void Setup(){
        emailSender = mock(EmailSender.class);
        userService = mock(UserService.class);
        passwordResetService = new PasswordResetServiceImpl(emailSender, userService);
    }

}
