package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private EmailSender emailSender;

    @Autowired
    public PasswordResetServiceImpl(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void resetPassword(String email) {
        emailSender.SendEmail("no-reply@booksreviewplatform.pl", email, "Password reset", "Your new pass:" );
    }
}
