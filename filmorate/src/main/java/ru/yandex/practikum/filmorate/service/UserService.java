package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.User;
import ru.yandex.practikum.filmorate.storage.InMemoryUserStorage;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final InMemoryUserStorage users;

    @Autowired
    public UserService(InMemoryUserStorage users) {
        this.users = users;
    }

    public boolean addFriend(int userId, int anotherUserId) {
        return users.getUsers().get(userId).getFriends().add(anotherUserId);
    }

    public boolean removeFriend(User user, User anotherUser) {
        return user.getFriends().remove(anotherUser.getId());
    }

    public Set<Integer> getFriends(User user) {
        return user.getFriends();
    }

    public List<User> getUsers() {
        return users.getUsers();
    }

    public User createUser(User user) {
        return users.add(user);
    }

}
