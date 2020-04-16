package com.uj.projects.booksplatform.error.exception;

import java.util.HashMap;
import java.util.Map;

public class DefaultRuntimeException extends RuntimeException {
    Map<String, String> errors;

    public DefaultRuntimeException(Map<String, String> errors) {
        this.errors = errors;
    }
    public DefaultRuntimeException(String parameter, String errorMessage){
        Map<String, String> errors = new HashMap<>();
        errors.put(parameter, errorMessage);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
