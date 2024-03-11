package ru.yandex.practikum.filmorate.storage;

import ru.yandex.practikum.filmorate.model.Film;
import ru.yandex.practikum.filmorate.model.User;

public interface FilmStorage {
    Film add(Film film);
    Film remove(Film film);
    Film update(Film film);
}
