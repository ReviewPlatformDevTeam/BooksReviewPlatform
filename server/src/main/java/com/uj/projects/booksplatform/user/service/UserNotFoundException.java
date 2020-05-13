package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;

import java.util.Map;

public class UserNotFoundException extends DefaultRuntimeException {
    public UserNotFoundException(Map<String, String> errors) {
        super(errors);
    }
}
