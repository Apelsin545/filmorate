package ru.yandex.practikum.filmorate.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.dao.FilmDao;
import ru.yandex.practikum.filmorate.model.Film;
import ru.yandex.practikum.filmorate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Component
public class FilmDaoImpl implements FilmDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film getFilmById(int id) {
        String sql = "select * from filmorate_film where id = ?";

        SqlRowSet user = jdbcTemplate.queryForRowSet(sql, id);
        if (user.next()) {
            return new Film(Integer.parseInt(Objects.requireNonNull(user.getString("id"))),
                    user.getString("name"),
                    user.getString("description"),
                    user.getString("genre"),
                    user.getString("mpa"),
                    LocalDate.parse(Objects.requireNonNull(user.getString("release_date")), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    Time.valueOf(LocalTime.parse(Objects.requireNonNull(user.getString("duration"))))
                    );
        } else {
            return null;
        }
    }

    @Override
    public List<Film> getAllFilms() {
        String sql = "select * from filmorate_film";

        return jdbcTemplate.query(sql, (rs, max) -> makeFilm(rs));
    }

    public Film makeFilm(ResultSet rs) throws SQLException {
        return new Film(Integer.parseInt(rs.getString("id")),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("genre"),
                rs.getString("mpa"),
                LocalDate.parse(Objects.requireNonNull(rs.getString("release_date")), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Time.valueOf(LocalTime.parse(Objects.requireNonNull(rs.getString("duration")))));
    }

    @Override
    public void createFilm(Film film) {
        jdbcTemplate.update("insert into filmorate_film (name, description, genre, mpa, release_date, duration) values (?, ? ,? ,? ,? ,?)",
                film.getName(),
                film.getDescription(),
                film.getGenre(),
                film.getMPA(),
                film.getReleaseDate(),
                film.getDuration());
    }

    @Override
    public void removeFilm(int id) {
        jdbcTemplate.update("delete from filmorate_film where id = ?", id);
    }
}
