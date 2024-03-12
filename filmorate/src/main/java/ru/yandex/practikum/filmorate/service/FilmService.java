package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.Film;
import ru.yandex.practikum.filmorate.storage.InMemoryFilmStorage;

@Service
public class FilmService {
    private final InMemoryFilmStorage films;

    @Autowired
    public FilmService(InMemoryFilmStorage films) {
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


}
