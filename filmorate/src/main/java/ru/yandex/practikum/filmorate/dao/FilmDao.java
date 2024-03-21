package ru.yandex.practikum.filmorate.dao;

import ru.yandex.practikum.filmorate.model.Film;

import java.util.List;

public interface FilmDao {
    Film getFilmById(int id);

    List<Film> getAllFilms();

    void createFilm(Film film);

    void removeFilm(int id);
}
