package com.uj.projects.booksplatform.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BadCredentialsException extends RuntimeException {
    private String errorMessage;
}
