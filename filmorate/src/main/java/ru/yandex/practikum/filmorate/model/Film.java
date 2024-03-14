package ru.yandex.practikum.filmorate.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Film {
    private final int id;

    private final Set<Integer> usersLikedFilm = new HashSet<>();

    @NotNull
    private String name;
    private String description;
    private String genre;
    private String MPA;

    private LocalDate releaseDate;
    private Duration duration;
}
