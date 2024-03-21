package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.Film;

import java.util.List;

@Service
public class FilmService {
    private final FilmStorage films;
    private final UserService userService;

    @Autowired
    public FilmService(FilmStorage films, UserService userService) {
        this.films = films;
        this.userService = userService;
    }

    public void addLike(int filmId, int userId) {
        films.getFilms()
                .get(filmId)
                .getUsersLikedFilm()
                .add(userService.getUserById(userId));
    }

    public void deleteLike(int filmId, int userId) {
        films.getFilms()
                .get(filmId)
                .getUsersLikedFilm()
                .remove(userId);
    }

    public List<Film> getPopularFilms(int count) {
        if (count == 0) count = 10;

        return films.getFilms()
                .values()
                .stream()
                .toList()
                .stream().sorted((film1, film2) -> (film1.getUsersLikedFilm().size() < film2.getUsersLikedFilm().size()) ? 1 : -1)
                .limit(count)
                .toList();
    }

    public List<Film> findAll() {
        return films.getFilms()
                .values()
                .stream()
                .toList();
    }

    public Film createFilm(Film film) {
        return films.add(film);
    }

    public Film removeFilm(Film film) {
        return films.remove(film);
    }

}
