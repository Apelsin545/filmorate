package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.User;
import ru.yandex.practikum.filmorate.storage.InMemoryUserStorage;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    public final InMemoryUserStorage users;

    @Autowired
    public UserService(InMemoryUserStorage users) {
        this.users = users;
    }

    public boolean addFriend(User user, User anotherUser) {
        if (anotherUser == null) return false;
        user.getFriends().add(anotherUser.getId());

        return true;
    }

    public boolean removeFriend(User user, User anotherUser) {
        if (anotherUser == null) return false;
        user.getFriends().remove(anotherUser.getId());

        return true;
    }

    public Set<Integer> getFriends(User user) {
        return user.getFriends();
    }

    public List<User> getUsers() {
        return users.getUsers().values().stream().toList();
    }

}
