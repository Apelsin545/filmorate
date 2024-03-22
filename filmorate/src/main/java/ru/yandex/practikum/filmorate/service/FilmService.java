package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.dao.FilmDao;
import ru.yandex.practikum.filmorate.dao.FilmLikeDao;
import ru.yandex.practikum.filmorate.model.Film;

import java.util.List;

@Service
public class FilmService {
    private final FilmDao filmDao;
    private final FilmLikeDao filmLikeDao;

    @Autowired
    public FilmService(FilmDao filmDao, FilmLikeDao filmLikeDao) {
        this.filmDao = filmDao;
        this.filmLikeDao = filmLikeDao;
    }

    public Film getFilmById(int id) {
        return filmDao.getFilmById(id);
    }

    public List<Film> getAllFilms() {
        return filmDao.getAllFilms();
    }

    public void createFilm(Film film) {
        filmDao.createFilm(film);
    }

    public void removeFilm(int filmId) {
        filmDao.removeFilm(filmId);
    }

    public void addLikeToFilm(int filmId, int userId) {
        filmLikeDao.addLikeToFilm(userId, filmId);
    }

    public void deleteLikeFromFilm(int filmId, int userId) {
        filmLikeDao.deleteLikeFromFilm(userId, filmId);
    }

    public List<Film> getPopularFilms(int max) {
        return filmLikeDao.getPopularFilms(max);
    }
}
