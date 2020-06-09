package com.uj.projects.booksplatform.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class DefaultRuntimeException extends RuntimeException {
    private Map<String, String> errors;
    private List<String> additionalMessages;


    public DefaultRuntimeException(Map<String, String> errors) {
        this.errors = errors;
    }

    public DefaultRuntimeException(String errorMessage) {
        additionalMessages = new LinkedList<>();
        additionalMessages.add(errorMessage);
    }
    public DefaultRuntimeException(String parameter, String errorMessage){
        Map<String, String> errors = new HashMap<>();
        errors.put(parameter, errorMessage);
        this.errors = errors;
    }
}
