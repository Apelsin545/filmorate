package ru.yandex.practikum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practikum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
public class FilmController {
    Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/films")
    public List<Film> findAll() {
        return new ArrayList<>(films.values());
    }

    @PostMapping("/film")
    public Film create(@Valid @RequestBody Film film) throws ValidationException {
        if (!Objects.equals(film.getName(), "") && film.getDescription().length() <= 200
                && film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 28))
                && !film.getDuration().isNegative()) {
            if (films.get(film.getId()) == null) log.info("Добавлен новый фильм: " + film);
            else log.info("Изменен существующий фильм: " + film);

            films.put(film.getId(), film);
        } else {
            if (films.get(film.getId()) == null) log.info("Ошибка добавления фильма: " + film);
            else log.info("Ошибка обновления фильма: " + film);

            throw new ValidationException("неправильное тело запроса");
        }
        return film;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> ValidationException(final ValidationException e) {

        return Map.of(
                "errorMessage", e.getMessage());
    }
}
