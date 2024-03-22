package ru.yandex.practikum.filmorate.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.dao.FriendDao;
import ru.yandex.practikum.filmorate.dao.UserDao;
import ru.yandex.practikum.filmorate.model.User;

import java.util.*;

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
        String sql = "select * from filmorate_friend where (user_from_id = ? and user_to_id = ?) or (user_from_id = ? and user_to_id = ?)";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userFrom, userTo, userTo, userFrom);

        if (rows.next()) {
            int userFromTable = Integer.parseInt(Objects.requireNonNull(rows.getString("user_from_id")));
            int userToTable = Integer.parseInt(Objects.requireNonNull(rows.getString("user_to_id")));
            boolean isAccepted = Boolean.parseBoolean(Objects.requireNonNull(rows.getString("is_accepted")));

            if (userFrom == userToTable && userTo == userFromTable && !isAccepted) {
                jdbcTemplate.update("update filmorate_friend set is_accepted = true where user_from_id = ? and user_to_id = ?", userTo, userFrom);
            }
        } else {
            jdbcTemplate.update("insert into filmorate_friend (user_from_id, user_to_id, is_accepted) values (?, ?, false)", userFrom, userTo);
        }
    }

    @Override
    public void rejectRequest(int userFrom, int userTo) {
        String sql = "select * from filmorate_friend where (user_from_id = ? and user_to_id = ?) or (user_from_id = ? and user_to_id = ?)";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userFrom, userTo, userTo, userFrom);

        if (rows.next()) {
            int userFromTable = Integer.parseInt(Objects.requireNonNull(rows.getString("user_from_id")));
            int userToTable = Integer.parseInt(Objects.requireNonNull(rows.getString("user_to_id")));

            if (userFrom == userToTable && userTo == userFromTable || userFrom == userFromTable && userTo == userToTable) {
                jdbcTemplate.update("delete from filmorate_friend where user_from_id = ? and user_to_id = ?", userTo, userFrom);
            }
        }
    }

    @Override
    public List<User> getCommonFriends(int userId, int otherId) {
        String sql = "select user_from_id, user_to_id from filmorate_friend where (user_from_id = ? or user_to_id = ? or user_from_id = ? or user_to_id = ?) and is_accepted = true";
        Map<Integer, Integer> users = new HashMap<>();
        List<User> answer = new ArrayList<>();

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId, otherId, otherId, userId);
        while (rows.next()) {
            int friendId = (Integer.parseInt(Objects.requireNonNull(rows.getString("user_from_id"))) == userId ||
                    Integer.parseInt(Objects.requireNonNull(rows.getString("user_from_id"))) == otherId) ?
                    Integer.parseInt(Objects.requireNonNull(rows.getString("user_to_id"))) :
                    Integer.parseInt(Objects.requireNonNull(rows.getString("user_from_id")));
            users.put(friendId, users.getOrDefault(friendId, 0) + 1);
        }

        for (Integer key : users.keySet()) {
            if (users.get(key) == 2) answer.add(userDao.getUserById(key));
        }

        return answer;
    }


}
