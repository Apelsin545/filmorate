package ru.yandex.practikum.filmorate.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.dao.UserDao;
import ru.yandex.practikum.filmorate.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int id) {
        String sql = "select * from filmorate_user where id = ?";

        SqlRowSet user = jdbcTemplate.queryForRowSet(sql, id);
        if (user.next()) {
            return new User(Integer.parseInt(Objects.requireNonNull(user.getString("id"))),
                    user.getString("email"),
                    user.getString("login"),
                    user.getString("name"),
                    LocalDate.parse(Objects.requireNonNull(user.getString("birthday")), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } else {
            return null;
        }


    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void removeUser(int id) {

    }
}
