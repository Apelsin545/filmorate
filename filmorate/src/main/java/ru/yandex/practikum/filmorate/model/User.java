package ru.yandex.practikum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private final int id;

    private final Set<Integer> friends = new HashSet<>();

    @Email
    private String email;

    @NotBlank
    @NotNull
    private String login;
    private String name;

    private LocalDate birthday;
}
