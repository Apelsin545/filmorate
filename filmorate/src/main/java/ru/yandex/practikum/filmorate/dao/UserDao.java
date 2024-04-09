package ru.yandex.practikum.filmorate.dao;

import ru.yandex.practikum.filmorate.model.User;

import java.util.List;

public interface UserDao {
    User getUserById(int id);

    List<User> getAllUsers();

    void createUser(User user);

    void removeUser(int id);
}
