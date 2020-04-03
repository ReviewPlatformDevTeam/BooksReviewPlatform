package com.uj.projects.booksplatform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    private String username;
    @NotBlank(message = "Hasło nie może być puste")
    private String password;
}
