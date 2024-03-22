package ru.yandex.practikum.filmorate.dao.Impl;

import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.dao.FilmDao;
import ru.yandex.practikum.filmorate.dao.FilmLikeDao;
import ru.yandex.practikum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FilmLikeDaoImpl implements FilmLikeDao {
    private final JdbcTemplate jdbcTemplate;
    private final FilmDao filmDao;

    @Autowired
    public FilmLikeDaoImpl(JdbcTemplate jdbcTemplate, FilmDao filmDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.filmDao = filmDao;
    }

    @Override
    public void addLikeToFilm(int userId, int filmId) {
        jdbcTemplate.update("insert into filmorate_friend (user_id, film_id) values (?, ?)", userId, filmId);
    }

    @Override
    public void deleteLikeFromFilm(int userId, int filmId) {
        jdbcTemplate.update("delete from filmorate_friend where user_id = ? and film_id = ?", userId, filmId);
    }

    @Override
    public List<Film> getPopularFilms(int max) {
        String sql = "select film_id, count(film_id) from filmorate_film_like group by film_id order by count(film_id) desc";
        List<Film> popularFilms = new ArrayList<>();

        SqlRowSet films = jdbcTemplate.queryForRowSet(sql);
        for (int i = 0; i < max && films.next(); i++) {
            popularFilms.add(filmDao.getFilmById(Integer.parseInt(Objects.requireNonNull(films.getString("film_id")))));
        }

        return popularFilms;
    }
}
