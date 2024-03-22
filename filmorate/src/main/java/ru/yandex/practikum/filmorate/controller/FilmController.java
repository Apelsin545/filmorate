package ru.yandex.practikum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practikum.filmorate.model.Film;
import ru.yandex.practikum.filmorate.service.FilmService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value = "/films", params = {"id"})
    public Film getFilmById(@RequestParam int id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/films/popular")
    public List<Film> getPopular(@RequestParam int count) {
        return filmService.getPopularFilms(count);
    }

    @PostMapping("/film")
    public void create(@Valid @RequestBody Film film) throws ValidationException {
        if (!Objects.equals(film.getName(), "") && film.getDescription().length() <= 200
                && film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 28))
                && !film.getDuration().isNegative()) {
            log.info("Добавлен новый фильм: " + film);

            filmService.createFilm(film);
        } else {
            log.info("Ошибка добавления фильма: " + film);

            throw new ValidationException("неправильное тело запроса");
        }
    }

    @DeleteMapping("/film")
    public void delete(@RequestParam int id) {
        filmService.removeFilm(id);
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLikeToFilm(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLikeFromFilm(id, userId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> ValidationException(final ValidationException e) {

        return Map.of(
                "errorMessage", e.getMessage());
    }
}
