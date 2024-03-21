package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
