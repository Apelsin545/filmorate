package ru.yandex.practikum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Film {
    private final int id;

    @NotNull
    private String name;
    private String description;
    private String genre;
    private String MPA;

    private LocalDate releaseDate;
    private Time duration;
}
