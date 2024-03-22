package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.dao.FriendDao;
import ru.yandex.practikum.filmorate.dao.Impl.UserDaoImpl;
import ru.yandex.practikum.filmorate.dao.UserDao;
import ru.yandex.practikum.filmorate.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDao userDao;
    private final FriendDao friendDao;

    @Autowired
    public UserService(UserDao userDao, FriendDao friendDao) {
        this.userDao = userDao;
        this.friendDao = friendDao;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public void removeUser(int userId) {
        userDao.removeUser(userId);
    }

    public List<User> getFriends(int userId) {
        return friendDao.getFriends(userId);
    }

    public void addFriend(int userFromId, int userToId) {
        friendDao.sendRequest(userFromId, userToId);
    }

    public void deleteFriend(int userFromId, int userToId) {
        friendDao.rejectRequest(userFromId, userToId);
    }
}
