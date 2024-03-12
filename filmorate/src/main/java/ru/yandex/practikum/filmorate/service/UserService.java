package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.User;
import ru.yandex.practikum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practikum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserStorage users;

    @Autowired
    public UserService(UserStorage users) {
        this.users = users;
    }

    public boolean addFriend(int userId, int anotherUserId) {
        return users.getUsers().get(userId).getFriends().add(anotherUserId);
    }

    public boolean removeFriend(int userId, int anotherUserId) {
        return users.getUsers().get(userId).getFriends().remove(anotherUserId);
    }

    public Set<Integer> getCommonFriends(int userId, int anotherUserId) {
        return users.getUsers()
                .get(userId)
                .getFriends()
                .stream()
                .filter(id -> users.getUsers()
                        .get(anotherUserId)
                        .getFriends().contains(id))
                .collect(Collectors.toSet());
    }

    public Set<Integer> getFriends(int userId) {
        return users.getUsers().get(userId).getFriends();
    }

    public List<User> getUsers() {
        return users.getUsers().values().stream().toList();
    }

    public User createUser(User user) {
        return users.add(user);
    }

}
