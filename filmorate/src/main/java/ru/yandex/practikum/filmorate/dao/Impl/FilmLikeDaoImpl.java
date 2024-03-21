package ru.yandex.practikum.filmorate.dao.Impl;

import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.dao.FilmLikeDao;
import ru.yandex.practikum.filmorate.model.Film;

import java.util.List;

@Component
public class FilmLikeDaoImpl implements FilmLikeDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmLikeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLikeToFilm(int userId, int filmId) {

    }

    @Override
    public void deleteLikeFromFilm(int userId, int filmId) {

    }

    @Override
    public List<Film> getPopularFilms(int max) {
        return null;
    }
}
