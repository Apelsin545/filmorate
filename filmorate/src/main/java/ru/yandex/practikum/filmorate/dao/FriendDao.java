package ru.yandex.practikum.filmorate.dao;

import ru.yandex.practikum.filmorate.model.User;

import java.util.List;

public interface FriendDao {
    List<User> getFriends(int userId);

    void sendRequest(int userFrom, int userTo);
    void rejectRequest(int userFrom, int userTo);

    void removeFriend(int userFrom, int userToRemove);
}
