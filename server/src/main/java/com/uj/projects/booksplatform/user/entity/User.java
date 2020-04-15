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

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be at least 5 characters")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, message = "Password must be at least 5 characters")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address format")
    @Column(name = "email")
    private String email;

}
