package com.uj.projects.booksplatform.error.handler;

import com.uj.projects.booksplatform.error.dto.ErrorResponse;
import com.uj.projects.booksplatform.error.exception.AlreadyExistsException;
import com.uj.projects.booksplatform.error.exception.BadCredentialsException;
import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;
import com.uj.projects.booksplatform.error.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(DefaultRuntimeException.class)
    public ErrorResponse handleValidationExceptions(DefaultRuntimeException ex) {
        return new ErrorResponse(ex.getErrors());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundExceptions(NotFoundException ex) {
        return new ErrorResponse(ex.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ErrorResponse handleBadCredentialsExceptions(BadCredentialsException ex) {
        return new ErrorResponse(ex.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistsException.class)
    public ErrorResponse handleAlreadyExistsExceptions(AlreadyExistsException ex) {
        return new ErrorResponse(ex.getErrorMessage());
    }
}
