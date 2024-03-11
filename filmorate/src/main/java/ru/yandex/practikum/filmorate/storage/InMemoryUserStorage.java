package ru.yandex.practikum.filmorate.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practikum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class InMemoryUserStorage implements UserStorage {
    Map<Integer, User> users = new HashMap<>();

    @Override
    public User add(User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public User remove(User user) {
        return users.remove(user.getId());
    }

    @Override
    public User update(User user) {
        return users.put(user.getId(), user);
    }
}
