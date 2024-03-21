package ru.yandex.practikum.filmorate.storage;

import ru.yandex.practikum.filmorate.model.Film;

import java.util.Map;

public interface FilmStorage {
    int a = 1;

    Film add(Film film);
    Film remove(Film film);
    Film update(Film film);

    Map<Integer, Film> getFilms();
}
