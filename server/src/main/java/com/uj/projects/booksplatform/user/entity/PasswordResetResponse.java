package com.uj.projects.booksplatform.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PasswordResetResponse {
    private boolean success;
    private String errorMessage;
}
