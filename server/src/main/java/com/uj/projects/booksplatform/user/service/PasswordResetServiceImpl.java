package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.error.exception.NotFoundException;
import com.uj.projects.booksplatform.user.EmailResources;
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
    public boolean resetPassword(String email) {
        String newPassword;
        try {
            newPassword = userService.resetPassword(email);
        } catch (NotFoundException e) {
            System.out.print("Could not find user with email: " + email);
            throw e;
        }
        return emailSender.SendEmail(EmailResources.EMAIL_SENDER_EMAIL_ADDRESS, email,
                EmailResources.PASSWORD_RESET_EMAIL_TOPIC,
                EmailResources.PASSWORD_RESET_EMAIL_CONTENT + newPassword );
    }
}
