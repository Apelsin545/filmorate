package ru.yandex.practikum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private LocalDate releaseDate;
    private Duration duration;
}
