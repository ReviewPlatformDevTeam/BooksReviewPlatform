package com.uj.projects.booksplatform.user.service;

public interface PasswordResetService {
    boolean resetPassword(String email) throws UserNotFoundException;
}
