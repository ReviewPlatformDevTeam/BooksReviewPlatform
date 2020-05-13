package com.uj.projects.booksplatform.error.exception;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class NotFoundException extends DefaultRuntimeException{
    public NotFoundException(Map<String, String> parameters){
        super(parameters);
    }

    public NotFoundException(String errorMessages){
        super(errorMessages);
    }
}
