package ru.yandex.practikum.filmorate.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.dao.FriendDao;
import ru.yandex.practikum.filmorate.dao.UserDao;
import ru.yandex.practikum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FriendDaoImpl implements FriendDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    @Autowired
    public FriendDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public List<User> getFriends(int userId) {
        List<User> friends = new ArrayList<>();
        String sql = "select user_from_id, user_to_id from filmorate_friend where (user_from_id = ? or user_to_id = ?) and (is_accepted = true)";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId, userId);

        while (rows.next()) {
            if (Integer.parseInt(Objects.requireNonNull(rows.getString("user_from_id"))) != userId) {
                friends.add(userDao.getUserById(Integer.parseInt(Objects.requireNonNull(rows.getString("user_from_id")))));
            } else {
                friends.add(userDao.getUserById(Integer.parseInt(Objects.requireNonNull(rows.getString("user_to_id")))));
            }
        }
        
        return friends;
    }

    @Override
    public void sendRequest(int userFrom, int userTo) {

    }

    @Override
    public void rejectRequest(int userFrom, int userTo) {

    }

    @Override
    public void removeFriend(int userFrom, int userToRemove) {

    }

    @Override
    public void acceptRequest(int userFrom, int userTo) {

    }
}
