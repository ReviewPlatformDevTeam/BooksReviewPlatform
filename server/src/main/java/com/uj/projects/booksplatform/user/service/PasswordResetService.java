package com.uj.projects.booksplatform.user.service;

public interface PasswordResetService {
    void resetPassword(String email) throws UserNotFoundException;
}
