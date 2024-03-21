package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserStorage users;

    @Autowired
    public UserService(UserStorage users) {
        this.users = users;
    }

    public User getUserById(int id) {
        return users.getUsers().get(id);
    }

    public boolean addFriend(int userId, int anotherUserId) {
        if (users.getUsers().get(userId).getFriends().get(anotherUserId) == null && users.getUsers().get(anotherUserId).getFriends().get(userId) == null) {
            users.getUsers().get(anotherUserId).getFriends().put(userId, "Not confirmed");

            return true;
        } else if (Objects.equals(users.getUsers().get(userId).getFriends().get(anotherUserId), "Not confirmed")) {
            users.getUsers().get(userId).getFriends().put(anotherUserId, "Confirmed");
            users.getUsers().get(anotherUserId).getFriends().put(userId, "Confirmed");

            return true;
        }

        return false;
    }

    public boolean removeFriend(int userId, int anotherUserId) {
        if (Objects.equals(users.getUsers().get(userId).getFriends().get(anotherUserId), "Not confirmed")) {
            users.getUsers().get(anotherUserId).getFriends().remove(userId);

            return true;
        } else if (Objects.equals(users.getUsers().get(userId).getFriends().get(anotherUserId), "Confirmed")) {
            users.getUsers().get(userId).getFriends().remove(anotherUserId);
            users.getUsers().get(anotherUserId).getFriends().remove(userId);

            return true;
        }

        return false;
    }

    public Set<Integer> getCommonFriends(int userId, int anotherUserId) {
        return users.getUsers()
                .get(userId)
                .getFriends()
                .keySet()
                .stream()
                .filter(id -> users.getUsers()
                        .get(anotherUserId)
                        .getFriends().containsKey(id) && Objects.equals(users.getUsers()
                        .get(anotherUserId)
                        .getFriends().get(id), "Confirmed"))
                .collect(Collectors.toSet());
    }

    public Set<Integer> getFriends(int userId) {
        return users.getUsers().get(userId).getFriends().keySet();
    }

    public List<User> getUsers() {
        return users.getUsers().values().stream().toList();
    }

    public User createUser(User user) {
        return users.add(user);
    }

}
