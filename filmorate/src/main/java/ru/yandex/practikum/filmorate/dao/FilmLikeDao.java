package ru.yandex.practikum.filmorate.dao;

import ru.yandex.practikum.filmorate.model.Film;

import java.util.List;

public interface FilmLikeDao {
    void addLikeToFilm(int userId, int filmId);
    void deleteLikeFromFilm(int userId, int filmId);

    List<Film> getPopularFilms(int max);
}
