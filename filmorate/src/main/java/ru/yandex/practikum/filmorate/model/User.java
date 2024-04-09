package ru.yandex.practikum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    private final int id;

    @Email
    private String email;

    @NotBlank
    @NotNull
    private String login;
    private String name;

    private LocalDate birthday;
}
