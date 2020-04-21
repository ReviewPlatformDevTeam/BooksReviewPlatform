package com.uj.projects.booksplatform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResult {
    private boolean Success;
    private String token;
}
