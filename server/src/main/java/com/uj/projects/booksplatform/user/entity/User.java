package com.uj.projects.booksplatform.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @Size(min = 5, message = "Nazwa użytkownika musi mieć co najmniej 5 znaków")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 5, message = "Hasło musi mieć co najmniej 5 znaków")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @Email(message = "Niepoprawny format adresu e-mail")
    @Column(name = "email")
    private String email;

}
