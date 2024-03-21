package ru.yandex.practikum.filmorate.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.model.Film;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class InMemoryFilmStorage implements FilmStorage {
    Map<Integer, Film> films = new HashMap<>();

    @Override
    public Film add(Film film) {
        return films.put(film.getId(), film);
    }

    @Override
    public Film remove(Film film) {
        return films.remove(film.getId());
    }

    @Override
    public Film update(Film film) {
        return films.put(film.getId(), film);
    }
}
