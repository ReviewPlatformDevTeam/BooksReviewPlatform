package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;
import com.uj.projects.booksplatform.error.exception.NotFoundException;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.wrappers.PasswordBuilderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    PasswordBuilderWrapper passwordBuilderWrapper;
    UserService userService;

    @Autowired
    PasswordResetServiceImpl(PasswordBuilderWrapper passwordBuilderWrapper, UserService userService){
        this.passwordBuilderWrapper = passwordBuilderWrapper;
        this.userService = userService;
    }

    @Override
    public boolean resetPassword(String email) {
        String newPassword = passwordBuilderWrapper.generatePassword();
        updateUserPassword(email, newPassword);
        return true;
    }

    private void updateUserPassword(String email, String newPassword){
        User user = userService.getUserByEmail(email);
        if(user == null){
            throw new NotFoundException("User with given email address: " + email + " doesn't exist");
        }
        user.setPassword(newPassword);
        userService.updateUser(user);
    }
}
