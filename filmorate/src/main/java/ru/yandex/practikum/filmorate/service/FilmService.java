package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.Film;
import ru.yandex.practikum.filmorate.storage.FilmStorage;
import ru.yandex.practikum.filmorate.storage.InMemoryFilmStorage;

import java.util.List;

@Service
public class FilmService {
    private final FilmStorage films;

    @Autowired
    public FilmService(FilmStorage films) {
        this.films = films;
    }

    public void addLike(int filmId, int userId) {
        films.getFilms()
                .get(filmId)
                .getUsersLikedFilm()
                .add(userId);
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
                .stream().sorted((film1, film2) -> (film1.getUsersLikedFilm().size() > film2.getUsersLikedFilm().size()) ? 1 : -1)
                .limit(count)
                .toList();
    }

    public List<Film> findAll() {
        return films.getFilms()
                .values()
                .stream()
                .toList();
    }


}
