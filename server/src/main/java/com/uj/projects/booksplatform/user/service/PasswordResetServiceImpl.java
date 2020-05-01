package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private EmailSender emailSender;
    private UserService userService;

    @Autowired
    public PasswordResetServiceImpl(EmailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    @Override
    public void resetPassword(String email) throws UserNotFoundException {
        String newPassword = null;
        try {
            newPassword = userService.resetPassword(email);
        } catch (UserNotFoundException e) {
            System.out.printf("Could not find user with email: " + email);
            throw e;
        }
        emailSender.SendEmail("no-reply@booksreviewplatform.pl", email, "Password reset for Books Review Platform", "Your new pass: " + newPassword );
    }
}
