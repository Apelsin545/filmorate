package ru.yandex.practikum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practikum.filmorate.model.User;
import ru.yandex.practikum.filmorate.storage.InMemoryUserStorage;

@Service
public class UserService {
    InMemoryUserStorage users;

    @Autowired
    public UserService(InMemoryUserStorage users) {
        this.users = users;
    }

    public boolean addFriend(User user, User anotherUser) {
        if (anotherUser == null) return false;
        user.getFriends().add(anotherUser.getId());
        
        return true;
    }


}
